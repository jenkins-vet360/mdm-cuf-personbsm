package mdm.cuf.personbsm.server;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import mdm.cuf.core.server.MdmCufCoreServerConfig;
import mdm.cuf.personbsm.server.rest.provider.PersonBsmServerRestProviderConfig;

/**
 * This is the main Spring config class for this module, it should declare any additional packages to scan declare any beans, supply
 * any other annotations etc.
 *
 * @author darias
 */

@Configuration
@ComponentScan(basePackages = "mdm.cuf.personbsm.server", excludeFilters = @Filter(Configuration.class))
@Import({MdmCufCoreServerConfig.class,PersonBsmServerRestProviderConfig.class})
public class PersonBsmServerConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService(){
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                //implement more advanced user details creation if you want, including a customized User object
                //and some utility to populate roles.  that is...if required within your system.
                final List<GrantedAuthority> authorities = new ArrayList<>();
                return new User(username, "n/a", authorities);
            }
        };
    }
    
}

