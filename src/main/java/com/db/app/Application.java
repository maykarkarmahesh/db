package com.db.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by mmaykarkar on 12/02/17.
 */
@SpringBootApplication(scanBasePackages={"com.db.*"})
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class,args);

    }
}
