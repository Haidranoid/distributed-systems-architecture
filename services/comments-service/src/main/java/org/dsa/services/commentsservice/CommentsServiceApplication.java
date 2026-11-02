package org.dsa.services.commentsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class CommentsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommentsServiceApplication.class, args);
    }

}
