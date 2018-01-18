package mdm.cuf.personbsm.server.processor.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import mdm.cuf.core.bio.AbstractBio;
import mdm.cuf.personbsm.api.PersonBsmErrorRequest;
import mdm.cuf.personbsm.server.dio.PersonBsmJob;
import mdm.cuf.personbsm.server.dio.PersonBsmTask;
import mdm.cuf.personbsm.server.dio.PersonBsmTaskId;

/**
 * Helper class to convert personBsm bios to dios
 *
 * @author darias
 */
@Component
public class PersonBsmTransformer {

    @Autowired
    private ObjectMapper objectMapper;

    
    /**
     * this method will convert will populate PersonBsmJob dio from PersonBsmErrorRequest
     * @param bio
     * @return
     * @throws JsonProcessingException
     */
    public PersonBsmJob bioToPersonJobDio(final PersonBsmErrorRequest bio) throws JsonProcessingException {
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
    public PersonBsmTask bioToPersonBsmTaskDio(final AbstractBio bio) {
        PersonBsmTask dio = new PersonBsmTask();
        PersonBsmTaskId dioId = new PersonBsmTaskId();
        dioId.setTxAuditId(bio.getTxAuditId());
        dio.setBsmTaskId(dioId);
        return dio;
    }

}
