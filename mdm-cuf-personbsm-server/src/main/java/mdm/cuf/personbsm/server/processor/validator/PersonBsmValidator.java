package mdm.cuf.personbsm.server.processor.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.google.common.base.Strings;

import mdm.cuf.core.messages.Message;
import mdm.cuf.core.messages.MessageSeverity;
import mdm.cuf.core.messages.MessageUtils;
import mdm.cuf.personbsm.api.PersonBsmErrorRequest;
import mdm.cuf.personbsm.server.rest.provider.PersonBsmMessageKeys;

/**
 * this class will do manual validation of incoming request since for now we are going to skip jsr303 validation since personbio schema
 * has validations fro most the fileds but at this point we migth get some bad data which is expected
 * 
 * @author diego.arias
 *
 */
@Component
public class PersonBsmValidator {

    @Autowired
    private MessageSource messageSource;

    /**
     * manual validation for request
     * 
     * @param bio
     * @return
     */
    public List<Message> basicValidation(final PersonBsmErrorRequest bio) {

        List<Message> messages = new ArrayList<>();
        if (Strings.isNullOrEmpty(bio.getTxAuditId())) {
            messages.add(constructBaseMessage(PersonBsmMessageKeys.TX_AUDIT_ID_NULL_KEY));
        }
        if (CollectionUtils.isEmpty(bio.getMessages())) {
            messages.add(constructBaseMessage(PersonBsmMessageKeys.MESSAGES_EMPTY_KEY));
        }
        if (bio.getPreValidationPersonBio() == null) {
            messages.add(constructBaseMessage(PersonBsmMessageKeys.PERSON_BIO_NULL_KEY));
        }
        if (bio.getPersonTraits() == null) {
            messages.add(constructBaseMessage(PersonBsmMessageKeys.PERSON_TRAITS_NULL_KEY));
        }
        if (Strings.isNullOrEmpty(bio.getCallbackUri())) {
            messages.add(constructBaseMessage(PersonBsmMessageKeys.CALL_BACK_URI_NULL_KEY));
        }
        return messages;
    }

    /**
     * Helper method construct error message
     * 
     * @param key
     * @return
     */
    private Message constructBaseMessage(String key) {
        Message msg = new Message();
        msg.setPotentiallySelfCorrectingOnRetry(false);
        msg.setSeverity(MessageSeverity.ERROR);
        msg.setKey(key);
        return MessageUtils.constructReturnMessage(msg, messageSource);
    }

}
