package com.db.app;

import com.db.entity.Shop;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mmaykarkar on 12/02/17.
 */
@SpringBootApplication(scanBasePackages={"com.db.*"})
public class Application {

    // used to save shop details globally at given instanace of module
    public static final List<Shop> SHOPS = new ArrayList<Shop>();
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
