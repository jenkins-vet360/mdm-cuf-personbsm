package mdm.cuf.personbsm.server;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;

import mdm.cuf.core.server.MdmCufCoreServerProperties;


/**
 * This Config Unit Test is a simple test to ensure the spring context loads for this project.
 * 
 * @author darias
 */
public class PersonBsmServerConfig_UnitTest extends PersonBsmServerSpringTestBase {

	@Autowired
	private MdmCufCoreServerProperties properties;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Test
	public void contextLoads() {
		Assert.assertNotNull(properties);
	}
	
	@Test
    public void userServiceUnitTest() {
        Assert.assertNotNull(userDetailsService.loadUserByUsername("sample"));
    }

}
