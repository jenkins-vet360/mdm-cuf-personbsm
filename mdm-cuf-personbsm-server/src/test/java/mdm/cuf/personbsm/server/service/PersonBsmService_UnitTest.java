package mdm.cuf.personbsm.server.service;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import mdm.cuf.core.messages.Message;
import mdm.cuf.core.messages.MessageSeverity;
import mdm.cuf.personbsm.api.PersonBio;
import mdm.cuf.personbsm.api.PersonBsmErrorRequest;
import mdm.cuf.personbsm.api.PersonBsmErrorResponse;
import mdm.cuf.personbsm.api.PersonTraits;
import mdm.cuf.personbsm.server.PersonBsmServerSpringTestBase;

public class PersonBsmService_UnitTest extends PersonBsmServerSpringTestBase {

    @Autowired
    private PersonBsmService personBsmService;

    @Test
    public void serviceRequestMissingFieldsTest() {

        PersonBsmErrorResponse response = personBsmService.personBsmErrorSubmit(getSubmitPersonBsmErrorRequest(false));
        Assert.assertNotNull(response.getMessages());
        Assert.assertEquals(5,response.getMessages().size());

    }
    
    @Test
    public void serviceRequestAllFieldsTest() {

        PersonBsmErrorResponse response = personBsmService.personBsmErrorSubmit(getSubmitPersonBsmErrorRequest(true));
        Assert.assertNotNull(response.getMessages());
        Assert.assertEquals(1,response.getMessages().size());
        Assert.assertEquals("GOT_IT",response.getMessages().get(0).getKey());

    }

    public static PersonBsmErrorRequest getSubmitPersonBsmErrorRequest(boolean complete) {
        PersonBsmErrorRequest request = new PersonBsmErrorRequest();
       
        
        if(complete) {
            
            request.setCallbackUri("http://sample.com");
            
            request.setTxAuditId(UUID.randomUUID().toString());
            
            PersonBio personBio = new PersonBio();
            personBio.setOriginatingSourceSystem("sample source system");
            request.setPreValidationPersonBio(personBio);
            
            Message msg = new Message();
            msg.setCode("301");
            msg.setSeverity(MessageSeverity.FATAL);
            request.getMessages().add(msg);
            
            PersonTraits personTraits = new PersonTraits();
            personTraits.setFirstName("Test");
            request.setPersonTraits(personTraits);
        }
        
        
        return request;
    }

}
