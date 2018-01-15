package mdm.cuf.personbsm.server;

import org.springframework.context.annotation.Import;

import mdm.cuf.core.server.AbstractMdmCufCoreServerSpringTest;

/**
 * This is the base test class for Spring tests within MDM CUF utilapp Server
 * 
 * @author jshrader
 */
@Import(PersonBsmServerConfig.class)
public abstract class PersonBsmServerSpringTestBase extends AbstractMdmCufCoreServerSpringTest {
    
}
