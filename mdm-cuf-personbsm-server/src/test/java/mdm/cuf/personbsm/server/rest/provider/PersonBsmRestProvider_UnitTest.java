package mdm.cuf.personbsm.server.rest.provider;

import java.util.Collections;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import mdm.cuf.core.server.AbstractMdmCufCoreHubOrMemberServerSpringTest;
import mdm.cuf.core.server.security.MdmCufCoreServerSecurityConfig;
import mdm.cuf.personbsm.api.PersonBsmCorrectedBioRequest;
import mdm.cuf.personbsm.api.PersonBsmErrorRequest;
import mdm.cuf.personbsm.api.PersonBsmErrorResponse;
import mdm.cuf.personbsm.server.PersonBsmServerSpringTestBase;
import mdm.cuf.personbsm.server.service.PersonBsmService_UnitTest;

public class PersonBsmRestProvider_UnitTest extends PersonBsmServerSpringTestBase {

    @Autowired
    private TestRestTemplate restTemplate;

    private final static String SUBMIT_ERROR_URL = "http://localhost:{port}/personbsm/v1/personbsmerrorsubmit";
    
    private final static String CORRECTED_ERROR_URL = "http://localhost:{port}/personbsm/v1/";

    private String personBsmSubmitErrorUrl;
    
    private String personBsmCorrectedErrorUrl;

    @PostConstruct
    public void postConstruct() {
        restTemplate.getRestTemplate().setInterceptors(Collections.singletonList((request, body, execution) -> {
            if (!request.getHeaders().containsKey(MdmCufCoreServerSecurityConfig.CUF_SYSTEM_HEADER)) {
                request.getHeaders().add(MdmCufCoreServerSecurityConfig.CUF_SYSTEM_HEADER,
                        AbstractMdmCufCoreHubOrMemberServerSpringTest.CUF_TEST_SYSTEM_NAME);
            }
            return execution.execute(request, body);
        }));

        personBsmSubmitErrorUrl = SUBMIT_ERROR_URL.replace("{port}", environment.getProperty("local.server.port"));
        
        personBsmCorrectedErrorUrl = CORRECTED_ERROR_URL.replace("{port}", environment.getProperty("local.server.port"));
    }

    @Test
    public void personBsmSubmitErrorTest() {

        HttpEntity<PersonBsmErrorRequest> requestEntity =
                new HttpEntity<>(PersonBsmService_UnitTest.getSubmitPersonBsmErrorRequest(true));

        ResponseEntity<PersonBsmErrorResponse> responseEntity =
                restTemplate.postForEntity(personBsmSubmitErrorUrl, requestEntity, PersonBsmErrorResponse.class);
        
        Assert.assertNotNull(responseEntity.getBody());
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertNotNull(responseEntity.getBody().getMessages());
        Assert.assertTrue(responseEntity.getBody().getMessages().size()==1);
        Assert.assertEquals("GOT_IT",responseEntity.getBody().getMessages().get(0).getKey());
    }
    
    @Test
    public void personBsmSubmitErrorTestEmail() {

        HttpEntity<PersonBsmErrorRequest> requestEntity =
                new HttpEntity<>(PersonBsmService_UnitTest.getSubmitPersonBsmErrorRequestEmail());

        ResponseEntity<PersonBsmErrorResponse> responseEntity =
                restTemplate.postForEntity(personBsmSubmitErrorUrl, requestEntity, PersonBsmErrorResponse.class);
        
        Assert.assertNotNull(responseEntity.getBody());
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertNotNull(responseEntity.getBody().getMessages());
        Assert.assertTrue(responseEntity.getBody().getMessages().size()==1);
        Assert.assertEquals("GOT_IT",responseEntity.getBody().getMessages().get(0).getKey());
    }
    
    @Test
    public void personBsmSubmitErrorTestAddress() {

        HttpEntity<PersonBsmErrorRequest> requestEntity =
                new HttpEntity<>(PersonBsmService_UnitTest.getSubmitPersonBsmErrorRequestAddress());

        ResponseEntity<PersonBsmErrorResponse> responseEntity =
                restTemplate.postForEntity(personBsmSubmitErrorUrl, requestEntity, PersonBsmErrorResponse.class);
        
        Assert.assertNotNull(responseEntity.getBody());
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertNotNull(responseEntity.getBody().getMessages());
        Assert.assertTrue(responseEntity.getBody().getMessages().size()==1);
        Assert.assertEquals("GOT_IT",responseEntity.getBody().getMessages().get(0).getKey());
    }
    
    @Test
    public void personBsmSubmitErrorTestTelephone() {

        HttpEntity<PersonBsmErrorRequest> requestEntity =
                new HttpEntity<>(PersonBsmService_UnitTest.getSubmitPersonBsmErrorRequestTelephone());

        ResponseEntity<PersonBsmErrorResponse> responseEntity =
                restTemplate.postForEntity(personBsmSubmitErrorUrl, requestEntity, PersonBsmErrorResponse.class);
        
        Assert.assertNotNull(responseEntity.getBody());
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertNotNull(responseEntity.getBody().getMessages());
        Assert.assertTrue(responseEntity.getBody().getMessages().size()==1);
        Assert.assertEquals("GOT_IT",responseEntity.getBody().getMessages().get(0).getKey());
    }
    
    @Test
    public void personBsmCorrectedErrorTest() {

        HttpEntity<PersonBsmCorrectedBioRequest> requestEntity =
                new HttpEntity<>(getPersonBsmCorrectedBioRequest());

        ResponseEntity<PersonBsmErrorResponse> responseEntity =
                restTemplate.postForEntity(personBsmCorrectedErrorUrl, requestEntity, PersonBsmErrorResponse.class);
        
        Assert.assertNotNull(responseEntity.getBody());
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertNotNull(responseEntity.getBody().getMessages());
        Assert.assertTrue(responseEntity.getBody().getMessages().size()==1);
        Assert.assertEquals("GOT_IT",responseEntity.getBody().getMessages().get(0).getKey());
    }
    
    public static PersonBsmCorrectedBioRequest getPersonBsmCorrectedBioRequest() {
        PersonBsmCorrectedBioRequest request = new PersonBsmCorrectedBioRequest();
        request.setTxAuditId(UUID.randomUUID().toString());
        return request;
    }

}
