package mdm.cuf.personbsm.server;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;

/**
 * Tests the utilapp home controller redirect as desired.
 * 
 * @author darias
 */
public class PersonBsmHomeController_UnitTest extends PersonBsmServerSpringTestBase {

    /** The rest template. */
    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * Home controller test.
     */
    @Test
    public void homeControllerTest() {
        //using rest-assured, test the root url
        given().port(localServerPort).basePath("/").when().get("").then()
            .statusCode(200)
            .and()
            .body("html.head.title", equalTo("Swagger UI"));

        //using the TestRestTemplate, ensure we get the redirect
       HttpHeaders headers = restTemplate.getForEntity("/", String.class).getHeaders();
       Assert.assertTrue(headers.getLocation().toString().contains("/swagger-ui.html"));
    }
    
}
