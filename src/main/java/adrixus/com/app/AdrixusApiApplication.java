package adrixus.com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@EnableEurekaClient
@ComponentScan( value = {
        "adrixus.com.controllers",
        "adrixus.com.services",
        "adrixus.com.datasource.config",
        "adrixus.com.datasource.config.util",
        "adrixus.com.repository",
        "adrixus.com.utility"
})
public class AdrixusApiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AdrixusApiApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(AdrixusApiApplication.class);
    }

}
