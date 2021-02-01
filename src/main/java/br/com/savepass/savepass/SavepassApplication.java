package br.com.savepass.savepass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class SavepassApplication {

    public static void main(String[] args) {
        SpringApplication.run(SavepassApplication.class, args);
    }

}
