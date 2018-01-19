package mdm.cuf.personbsm.server.validator;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import mdm.cuf.core.messages.Message;
import mdm.cuf.personbsm.server.PersonBsmServerSpringTestBase;
import mdm.cuf.personbsm.server.service.PersonBsmService_UnitTest;

public class PersonBsmValidator_UnitTest extends PersonBsmServerSpringTestBase {
    
    @Autowired
    private PersonBsmValidator validator;
    
    @Test
    public void serviceRequestMissingFieldsTest() {

        List<Message> messages = validator.basicValidation(PersonBsmService_UnitTest.getSubmitPersonBsmErrorRequest(false));
        Assert.assertNotNull(messages);
        Assert.assertEquals(5,messages.size());

    }
    
    @Test
    public void serviceRequestAllFieldsTest() {

        List<Message> messages = validator.basicValidation(PersonBsmService_UnitTest.getSubmitPersonBsmErrorRequest(true));
        Assert.assertNotNull(messages);
        Assert.assertEquals(0,messages.size());

    }

}
