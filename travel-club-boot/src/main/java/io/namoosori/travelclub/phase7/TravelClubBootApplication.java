package io.namoosori.travelclub.phase7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableCaching(proxyTargetClass = true)
@SpringBootApplication
public class TravelClubBootApplication {
    //
    public static void main(String[] args) {
        SpringApplication.run(TravelClubBootApplication.class, args);
    }
}
