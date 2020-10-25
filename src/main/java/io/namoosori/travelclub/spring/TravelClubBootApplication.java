package io.namoosori.travelclub.spring;

import io.namoosori.travelclub.spring.ui.menu.MainMenu;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
public class TravelClubBootApplication {
    //
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TravelClubConfig.class);

        MainMenu mainMenu = context.getBean("mainMenu", MainMenu.class);
        mainMenu.show();
    }
}
