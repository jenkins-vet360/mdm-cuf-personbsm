package mdm.cuf.personbsm.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * This is the actual Spring boot application that loads up the entire app.
 *
 * @author darias
 */

@SpringBootApplication
@Import(PersonBsmServerConfig.class)
@ComponentScan(basePackages = "mdm.cuf.personbsm.server", excludeFilters = @Filter(Configuration.class))
public class PersonBsmServerApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        return application.sources(PersonBsmServerApplication.class);
    }

    public static void main(final String[] args) {
        new SpringApplicationBuilder(PersonBsmServerApplication.class).build().run(args);
    }
}


