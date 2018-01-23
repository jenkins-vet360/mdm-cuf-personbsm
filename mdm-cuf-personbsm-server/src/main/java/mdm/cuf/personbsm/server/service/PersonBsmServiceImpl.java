package mdm.cuf.personbsm.server.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;

import mdm.cuf.core.bio.AbstractBio;
import mdm.cuf.core.exception.CufCoreRuntimeException;
import mdm.cuf.core.messages.Message;
import mdm.cuf.core.messages.MessageSeverity;
import mdm.cuf.core.util.Defense;
import mdm.cuf.personbsm.api.PersonBsmErrorRequest;
import mdm.cuf.personbsm.api.PersonBsmErrorResponse;
import mdm.cuf.personbsm.server.entity.PersonBsmJob;
import mdm.cuf.personbsm.server.entity.PersonBsmJobStatus;
import mdm.cuf.personbsm.server.entity.PersonBsmTask;
import mdm.cuf.personbsm.server.entity.PersonBsmTaskId;
import mdm.cuf.personbsm.server.entity.repository.PersonBsmJobRepository;
import mdm.cuf.personbsm.server.entity.repository.PersonBsmTaskRepository;
import mdm.cuf.personbsm.server.exception.PersonBsmErrorKeys;
import mdm.cuf.personbsm.server.validator.PersonBsmValidator;

/**
 * Service class to handle handle whole flow including validation , persisting data and sending request to bsm system
 * 
 * @author diego.arias
 *
 */
@Component
public class PersonBsmServiceImpl implements PersonBsmService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonBsmServiceImpl.class);

    @Autowired
    private PersonBsmValidator validator;

    @Autowired
    private PersonBsmJobRepository personBsmJobRepository;

    @Autowired
    private PersonBsmTaskRepository personBsmTaskRepository;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * this method will process the request
     * 
     * @param request
     * @return
     */
    @Transactional
    public PersonBsmErrorResponse personBsmErrorSubmit(PersonBsmErrorRequest request) {
        Defense.notNull(request, "PersonBsmErrorRequest cannot be null.");

        PersonBsmErrorResponse response = new PersonBsmErrorResponse();

        response.setMessages(validator.basicValidation(request));
        if (response.hasErrors() || response.hasFatals()) {
            return response;
        }

        personBsmJobRepository.save(requestToPersonJobEntity(request));

        getBioErrorTargets(request)
                .forEach(bioDetail -> personBsmTaskRepository.save(requestToPersonBsmTaskEntity(bioDetail.getBio().getTxAuditId(),
                        bioDetail.getBio().getClass().getTypeName(), bioDetail.getIndex(), bioDetail.getErrorMessage())));

        response.addMessage(new Message(MessageSeverity.INFO, "GOT_IT",
                "Not sure what sort of response we want to send back to caller, any form of tx for them to use for tracking?!?!"));
        return response;
    }

    /**
     * this method will match error messages to graph fields returning a list of subbios with its specific error message
     * 
     * @param request
     * @return
     */
    private List<PersonBsmTaskDetail> getBioErrorTargets(PersonBsmErrorRequest request) {
        List<PersonBsmTaskDetail> bioTargets = new ArrayList<>();
        Map<String, PersonBsmTaskDetail> fieldsMap = getBiosMap(request);
        for (Message msg : request.getMessages()) {
            String keyCheck = !Strings.isNullOrEmpty(msg.getKey()) && msg.getKey().contains(".")
                    ? msg.getKey().substring(0, msg.getKey().indexOf("."))
                    : "";
            if (fieldsMap.containsKey(keyCheck)) {
                PersonBsmTaskDetail detail = fieldsMap.get(keyCheck);
                try {
                    detail.setErrorMessage(objectMapper.writeValueAsString(msg));
                } catch (JsonProcessingException e) {
                    LOGGER.error("Error converting PersonBsmErrorRequest to JSON", e);
                }
                bioTargets.add(detail);
            }
        }
        if (CollectionUtils.isEmpty(bioTargets)) {
            LOGGER.error("Not able to find any bio targets");
            throw new CufCoreRuntimeException(PersonBsmErrorKeys.INVALID_REQUEST);
        }
        return bioTargets;
    }

    /**
     * this method will convert will populate PersonBsmJob entity from PersonBsmErrorRequest
     * 
     * @param bio
     * @return
     * @throws JsonProcessingException
     */
    private PersonBsmJob requestToPersonJobEntity(final PersonBsmErrorRequest bio) {
        PersonBsmJob entity = new PersonBsmJob();
        try {
            entity.setOrigTxRequest(objectMapper.writeValueAsString(bio));
        } catch (JsonProcessingException e) {
            LOGGER.error("Not able to convert Request to JSON",e);
            throw new CufCoreRuntimeException(PersonBsmErrorKeys.PARSE_REQUEST_EXCEPTION);
        }
        entity.setOrigTxAuditId(bio.getTxAuditId());
        entity.setOrigTxSrcSys(bio.getPreValidationPersonBio().getOriginatingSourceSystem());
        entity.setStatus(PersonBsmJobStatus.IN_PROGRESS);
        entity.setCallbackUri(bio.getCallbackUri());
        return entity;
    }

    /**
     * this method will convert will populate PersonBsmTask dio from PersonBsmErrorRequest
     * 
     * @param bio
     * @return
     */
    private PersonBsmTask requestToPersonBsmTaskEntity(String txAuditId, String bioType, int index, String messages) {
        PersonBsmTask entity = new PersonBsmTask();
        PersonBsmTaskId entityId = new PersonBsmTaskId();
        entityId.setTxAuditId(txAuditId);
        entityId.setBioType(bioType);
        entityId.setBioIndex(index);
        entity.setBsmTaskId(entityId);
        entity.setMessages(messages);
        return entity;
    }

    private Map<String, PersonBsmTaskDetail> getBiosMap(PersonBsmErrorRequest request) {
        Map<String, PersonBsmTaskDetail> biosMap = new HashMap<>();
        biosMap.put("personBio", createBioDetail(request.getPreValidationPersonBio(), 0));
        populateSubBios(request.getPreValidationPersonBio().getAddresses(), biosMap, "addresses");
        populateSubBios(request.getPreValidationPersonBio().getEmails(), biosMap, "emails");
        populateSubBios(request.getPreValidationPersonBio().getTelephones(), biosMap, "telephones");
        return biosMap;
    }

    private void populateSubBios(List subBios, Map<String, PersonBsmTaskDetail> biosMap, String prefix) {
        if (!CollectionUtils.isEmpty(subBios)) {
            for (int i = 0; i < subBios.size(); i++) {
                biosMap.put(prefix + "[" + i + "]", createBioDetail((AbstractBio) subBios.get(i), i));
            }
        }

    }

    private PersonBsmTaskDetail createBioDetail(AbstractBio bio, int index) {
        PersonBsmTaskDetail detail = new PersonBsmTaskDetail();
        detail.setBio(bio);
        detail.setIndex(index);
        return detail;

    }

}
