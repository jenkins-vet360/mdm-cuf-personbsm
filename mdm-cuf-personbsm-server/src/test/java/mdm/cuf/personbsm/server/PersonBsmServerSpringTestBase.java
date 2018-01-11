package mdm.cuf.personbsm.server;

import org.springframework.test.context.ActiveProfiles;

import mdm.cuf.core.server.AbstractMdmCufCoreServerSpringTest;
import mdm.cuf.core.server.MdmCufCoreServerProfiles;

/**
 * This is the base test class for Spring tests within MDM CUF utilapp Server
 * 
 * @author jshrader
 */
@ActiveProfiles(MdmCufCoreServerProfiles.MODE_UTIL_MICROSERVICE)
public abstract class PersonBsmServerSpringTestBase extends AbstractMdmCufCoreServerSpringTest {
    
}
