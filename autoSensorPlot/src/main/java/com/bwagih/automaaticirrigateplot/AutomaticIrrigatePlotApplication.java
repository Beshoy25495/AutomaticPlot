package com.bwagih.automaaticirrigateplot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@EnableSwagger2
public class AutomaticIrrigatePlotApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutomaticIrrigatePlotApplication.class, args);
    }

//    @Bean
//    public Docket productApi() {
//        return new Docket(DocumentationType.SWAGGER_2).select()
//                .apis(RequestHandlerSelectors.basePackage("com.bwagih.automaaticirrigateplot")).build();
//    }

}
