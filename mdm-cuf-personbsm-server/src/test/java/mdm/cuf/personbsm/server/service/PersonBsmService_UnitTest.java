package mdm.cuf.personbsm.server.service;

import java.util.Date;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import mdm.cuf.core.messages.Message;
import mdm.cuf.core.messages.MessageSeverity;
import mdm.cuf.personbsm.api.AddressBio;
import mdm.cuf.personbsm.api.AddressPOU;
import mdm.cuf.personbsm.api.EmailBio;
import mdm.cuf.personbsm.api.EmailStatusCode;
import mdm.cuf.personbsm.api.PersonBio;
import mdm.cuf.personbsm.api.PersonBsmErrorRequest;
import mdm.cuf.personbsm.api.PersonBsmErrorResponse;
import mdm.cuf.personbsm.api.PersonTraits;
import mdm.cuf.personbsm.api.TelephoneBio;
import mdm.cuf.personbsm.api.TelephoneConnectionStatusCode;
import mdm.cuf.personbsm.api.TelephoneType;
import mdm.cuf.personbsm.server.PersonBsmServerSpringTestBase;

public class PersonBsmService_UnitTest extends PersonBsmServerSpringTestBase {

    @Autowired
    private PersonBsmService personBsmService;

    @Test
    public void serviceRequestMissingFieldsTest() {

        PersonBsmErrorResponse response = personBsmService.personBsmErrorSubmit(getSubmitPersonBsmErrorRequest(false));
        Assert.assertNotNull(response.getMessages());
        Assert.assertEquals(5, response.getMessages().size());

    }

    @Test
    public void serviceRequestAllFieldsTest() {

        PersonBsmErrorResponse response = personBsmService.personBsmErrorSubmit(getSubmitPersonBsmErrorRequest(true));
        Assert.assertNotNull(response.getMessages());
        Assert.assertEquals(1, response.getMessages().size());
        Assert.assertEquals("GOT_IT", response.getMessages().get(0).getKey());

    }

    public static PersonBsmErrorRequest getSubmitPersonBsmErrorRequest(boolean complete) {
        PersonBsmErrorRequest request = new PersonBsmErrorRequest();

        if (complete) {

            request.setCallbackUri("http://sample.com");
            String txAuditId = UUID.randomUUID().toString();
            request.setTxAuditId(txAuditId);

            PersonBio personBio = new PersonBio();
            personBio.setOriginatingSourceSystem("VETSGOV");
            personBio.setSourceDate(new Date());
            personBio.setSourceSystemUser("VETSGOV");
            TelephoneBio tel = new TelephoneBio();
            tel.setAreaCode("305");
            tel.setConnectionStatusCode(TelephoneConnectionStatusCode.NO_KNOWN_PROBLEM);
            tel.setCountryCode("1");
            tel.setCreateDate(new Date());
            tel.setInternationalIndicator(false);
            tel.setPhoneNumber("3055555555");
            tel.setPhoneType(TelephoneType.MOBILE);
            tel.setSourceDate(new Date());
            tel.setSourceSystem("VETSGOV");
            tel.setSourceSystemUser("VETSGOV");
            tel.setTxAuditId(txAuditId);
            request.setPreValidationPersonBio(personBio);

            Message msg = new Message();
            msg.setCode("PHON207");
            msg.setSeverity(MessageSeverity.ERROR);
            msg.setKey("telephones[0].CheckDomesticPhoneNumber");
            msg.setText("Domestic phone number size must be 7 characters, and can not start with a 0 or 1.");
            request.getMessages().add(msg);

            PersonTraits personTraits = new PersonTraits();
            personTraits.setFirstName("Test");
            request.setPersonTraits(personTraits);
        }

        return request;
    }

    public static PersonBsmErrorRequest getSubmitPersonBsmErrorRequestTelephone() {
        PersonBsmErrorRequest request = new PersonBsmErrorRequest();
        String txAuditId = UUID.randomUUID().toString();
        request.setCallbackUri("http://sample.com");

        request.setTxAuditId(txAuditId);

        PersonBio personBio = new PersonBio();
        personBio.setOriginatingSourceSystem("VETSGOV");
        personBio.setSourceDate(new Date());
        personBio.setSourceSystemUser("VETSGOV");
        TelephoneBio tel = new TelephoneBio();
        tel.setAreaCode("305");
        tel.setConnectionStatusCode(TelephoneConnectionStatusCode.NO_KNOWN_PROBLEM);
        tel.setCountryCode("1");
        tel.setCreateDate(new Date());
        tel.setInternationalIndicator(false);
        tel.setPhoneNumber("7868532458");
        tel.setPhoneType(TelephoneType.MOBILE);
        tel.setSourceDate(new Date());
        tel.setSourceSystem("VETSGOV");
        tel.setSourceSystemUser("VETSGOV");
        tel.setTxAuditId(txAuditId);
        personBio.getTelephones().add(tel);
        request.setPreValidationPersonBio(personBio);

        Message msg = new Message();
        msg.setCode("PHON207");
        msg.setSeverity(MessageSeverity.ERROR);
        msg.setKey("telephones[0].CheckDomesticPhoneNumber");
        msg.setText("Domestic phone number size must be 7 characters, and can not start with a 0 or 1.");
        request.getMessages().add(msg);

        PersonTraits personTraits = new PersonTraits();
        personTraits.setFirstName("Test");
        request.setPersonTraits(personTraits);

        return request;
    }

    public static PersonBsmErrorRequest getSubmitPersonBsmErrorRequestEmail() {
        PersonBsmErrorRequest request = new PersonBsmErrorRequest();
        String txAuditId = UUID.randomUUID().toString();
        request.setCallbackUri("http://sample.com");

        request.setTxAuditId(txAuditId);

        PersonBio personBio = new PersonBio();
        personBio.setOriginatingSourceSystem("VETSGOV");
        personBio.setSourceDate(new Date());
        personBio.setSourceSystemUser("VETSGOV");
        EmailBio email = new EmailBio();
        email.setEmailPermInd(true);
        email.setEmailStatusCode(EmailStatusCode.NO_KNOWN_PROBLEM);
        email.setEmailAddressText("bademail");
        email.setSourceDate(new Date());
        email.setSourceSystem("VETSGOV");
        email.setSourceSystemUser("VETSGOV");
        email.setTxAuditId(txAuditId);
        personBio.getEmails().add(email);
        request.setPreValidationPersonBio(personBio);

        Message msg = new Message();
        msg.setCode("EMAIL305");
        msg.setSeverity(MessageSeverity.ERROR);
        msg.setKey("emails[0].CheckEmailAddress");
        msg.setText("EmailAddressText cannot have 2 @ symbols, must have at least one period '.' after the @ character, and cannot have  '.%' or '%.' or '%..%' or \\\" ( ) , : ; < > @ [ ]  or space unless in a quoted string in the local part.");
        request.getMessages().add(msg);

        PersonTraits personTraits = new PersonTraits();
        personTraits.setFirstName("Test");
        request.setPersonTraits(personTraits);

        return request;
    }

    public static PersonBsmErrorRequest getSubmitPersonBsmErrorRequestAddress() {
        PersonBsmErrorRequest request = new PersonBsmErrorRequest();
        String txAuditId = UUID.randomUUID().toString();
        request.setCallbackUri("http://sample.com");

        request.setTxAuditId(txAuditId);

        PersonBio personBio = new PersonBio();
        personBio.setOriginatingSourceSystem("VETSGOV");
        personBio.setSourceDate(new Date());
        personBio.setSourceSystemUser("VETSGOV");
        AddressBio address = new AddressBio();
        
        address.setAddressLine1("2900 S Quincy St.");
        address.setAddressLine2("#410");
        address.setAddressPOU(AddressPOU.CORRESPONDENCE);
        address.setAddressType("Residential");
        address.setCityName("Arlington");
        address.setConfidenceScore("98");
        address.setCountryCodeFIPS("abc");
        address.setCountryCodeISO2("abc");
        address.setCountryCodeISO3("123");
        address.setCountryName("USA");
        address.setZipCode5("22206");
        address.setStateCode("VA");
        address.setSourceDate(new Date());
        address.setSourceSystem("VETSGOV");
        address.setSourceSystemUser("VETSGOV");
        address.setTxAuditId(txAuditId);
        personBio.getAddresses().add(address);
        request.setPreValidationPersonBio(personBio);

        Message msg = new Message();
        msg.setCode("ADDR137");
        msg.setSeverity(MessageSeverity.ERROR);
        msg.setKey("addresses[0].countryCodeISO3.Pattern");
        msg.setText("\"must match \\\"^[a-zA-Z]+$\\\"");
        request.getMessages().add(msg);

        PersonTraits personTraits = new PersonTraits();
        personTraits.setFirstName("Test");
        request.setPersonTraits(personTraits);

        return request;
    }

}
