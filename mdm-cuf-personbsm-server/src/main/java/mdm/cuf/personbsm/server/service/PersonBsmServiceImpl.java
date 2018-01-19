package mdm.cuf.personbsm.server.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import mdm.cuf.core.bio.AbstractBio;
import mdm.cuf.core.messages.Message;
import mdm.cuf.core.messages.MessageSeverity;
import mdm.cuf.core.util.Defense;
import mdm.cuf.personbsm.api.PersonBsmErrorRequest;
import mdm.cuf.personbsm.api.PersonBsmErrorResponse;
import mdm.cuf.personbsm.server.entity.PersonBsmJob;
import mdm.cuf.personbsm.server.entity.PersonBsmTask;
import mdm.cuf.personbsm.server.entity.PersonBsmTaskId;
import mdm.cuf.personbsm.server.entity.repository.PersonBsmJobRepository;
import mdm.cuf.personbsm.server.entity.repository.PersonBsmTaskRepository;
import mdm.cuf.personbsm.server.validator.PersonBsmValidator;


/**
 * Service class to handle handle whole flow including validation , persisting data and sending request to bsm system
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
     * @param request
     * @return
     */
    @Transactional
    public PersonBsmErrorResponse personBsmErrorSubmit(PersonBsmErrorRequest request) {
        Defense.notNull(request, "PersonBsmErrorRequest cannot be null.");

        PersonBsmErrorResponse response = new PersonBsmErrorResponse();
        
        response.setMessages(validator.basicValidation(request));
        if(response.hasErrors() || response.hasFatals()){
            return response;
        }

        PersonBsmJob personJobBsm = null;
        try {
            personJobBsm = requestToPersonJobEntity(request);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error converting PersonBsmErrorRequest to JSON", e);
            Message msg = new Message();
            msg.setPotentiallySelfCorrectingOnRetry(false);
            msg.setSeverity(MessageSeverity.ERROR);
            msg.setText("Error converting PersonBsmErrorRequest to JSON");;
            response.addMessage(msg);
            return response;
        }

        personBsmJobRepository.save(personJobBsm);
        
        getBioErrorTargets(request).forEach(bio -> personBsmTaskRepository.save(requestToPersonBsmTaskEntity(bio)));

        response.addMessage(new Message(MessageSeverity.INFO, "GOT_IT",
                "Not sure what sort of response we want to send back to caller, any form of tx for them to use for tracking?!?!"));
        return response;
    }

    
    /**
     * this method will match error messages to graph fields returning a list of subbios with its specific error message
     * @param request
     * @return
     */
    private List<AbstractBio> getBioErrorTargets(PersonBsmErrorRequest request) {
        List<AbstractBio> bioTargets = new ArrayList<>();
        // To do
        return bioTargets;
    }
    
    /**
     * this method will convert will populate PersonBsmJob entity from PersonBsmErrorRequest
     * @param bio
     * @return
     * @throws JsonProcessingException
     */
    private PersonBsmJob requestToPersonJobEntity(final PersonBsmErrorRequest bio) throws JsonProcessingException {
        PersonBsmJob dio = new PersonBsmJob();
        dio.setOrigTxRequest(objectMapper.writeValueAsString(bio));
        dio.setOrigTxAuditId(bio.getTxAuditId());
        dio.setOrigTxSrcSys(bio.getPreValidationPersonBio().getOriginatingSourceSystem());
        return dio;
    }

    /**
     * this method will convert will populate PersonBsmTask dio from PersonBsmErrorRequest
     * @param bio
     * @return
     */
    private PersonBsmTask requestToPersonBsmTaskEntity(final AbstractBio bio) {
        PersonBsmTask entity = new PersonBsmTask();
        PersonBsmTaskId entityId = new PersonBsmTaskId();
        entityId.setTxAuditId(bio.getTxAuditId());
        entity.setBsmTaskId(entityId);
        return entity;
    }


}
