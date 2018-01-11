package mdm.cuf.personbsm.server;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import mdm.cuf.core.server.AbstractMdmCufCoreServerProperties;

/**
 * Application properties, they can be wired up by using mdm-cuf-personbsm placeholder in yml
 *
 * @author darias
 */

@Component
@ConfigurationProperties(prefix = "mdm-cuf-personbsm")
public class PersonBsmServerProperties extends AbstractMdmCufCoreServerProperties {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -901411920058806029L;
    
    private String demoPropertyOne;

    public String getDemoPropertyOne() {
        return demoPropertyOne;
    }

    public void setDemoPropertyOne(final String demoPropertyOne) {
        this.demoPropertyOne = demoPropertyOne;
    }

}
