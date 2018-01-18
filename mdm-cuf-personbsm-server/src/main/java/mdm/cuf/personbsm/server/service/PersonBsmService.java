package mdm.cuf.personbsm.server.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.JsonProcessingException;

import mdm.cuf.core.bio.AbstractBio;
import mdm.cuf.core.messages.Message;
import mdm.cuf.core.messages.MessageSeverity;
import mdm.cuf.core.util.Defense;
import mdm.cuf.personbsm.api.PersonBsmErrorRequest;
import mdm.cuf.personbsm.api.PersonBsmErrorResponse;
import mdm.cuf.personbsm.server.dio.PersonBsmJob;
import mdm.cuf.personbsm.server.dio.repository.PersonBsmJobRepository;
import mdm.cuf.personbsm.server.dio.repository.PersonBsmTaskRepository;
import mdm.cuf.personbsm.server.processor.transformer.PersonBsmTransformer;
import mdm.cuf.personbsm.server.processor.validator.PersonBsmValidator;


/**
 * Service class to handle handle whole flow including validation , persisting data and sending request to bsm system
 * @author diego.arias
 *
 */
@Component
public class PersonBsmService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonBsmService.class);

    @Autowired
    private PersonBsmValidator validator;

    @Autowired
    private PersonBsmJobRepository personBsmJobRepository;

    @Autowired
    private PersonBsmTaskRepository personBsmTaskRepository;

    @Autowired
    private PersonBsmTransformer personBsmBioToDioTransformer;

    
    /**
     * this method will process the request
     * @param request
     * @return
     */
    @Transactional
    public PersonBsmErrorResponse personBsmErrorSubmit(PersonBsmErrorRequest request) {
        Defense.notNull(request, "PersonBsmErrorRequest cannot be null.");

        PersonBsmErrorResponse response = new PersonBsmErrorResponse();
        List<Message> errorList = validator.basicValidation(request);
        if (!CollectionUtils.isEmpty(errorList) && checkErrorListHasFailures(errorList)) {
            response.addMessages(errorList);
            return response;
        }

        List<AbstractBio> errorBiosTargets = getBioErrorTargets(request);
        if (CollectionUtils.isEmpty(errorBiosTargets)) {
            LOGGER.error("Not able to find error bios for this request");
            Message msg = new Message();
            msg.setPotentiallySelfCorrectingOnRetry(false);
            msg.setSeverity(MessageSeverity.FATAL);
            msg.setText("Not able to find a error bios for this request");;
            response.addMessage(msg);
            return response;
        }

        errorBiosTargets.forEach(bio -> personBsmTaskRepository.save(personBsmBioToDioTransformer.bioToPersonBsmTaskDio(bio)));

        PersonBsmJob personJobBsm = null;
        try {
            personJobBsm = personBsmBioToDioTransformer.bioToPersonJobDio(request);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error converting PersonBsmErrorRequest to JSON", e);
            Message msg = new Message();
            msg.setPotentiallySelfCorrectingOnRetry(false);
            msg.setSeverity(MessageSeverity.FATAL);
            msg.setText("Error converting PersonBsmErrorRequest to JSON");;
            response.addMessage(msg);
            return response;
        }

        personBsmJobRepository.save(personJobBsm);

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
     * helper method to check for fatal errors
     * @param errors
     * @return
     */
    public boolean checkErrorListHasFailures(final List<Message> errors) {
        if (CollectionUtils.isEmpty(errors))
            return false;
        boolean hasErrors = false;
        for (final Message message : errors) {
            switch (message.getSeverity()) {
            case ERROR:
                hasErrors = true;
                break;
            case FATAL:
                hasErrors = true;
                break;
            default:
                // skip
            }
            if (hasErrors)
                break;
        }
        return hasErrors;
    }

}
