package io.namoosori.travelclub.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class TravelClubBootApplication {
    //
    public static void main(String[] args) {
        SpringApplication.run(TravelClubBootApplication.class, args);
    }
}
