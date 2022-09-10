package com.study.essentialguide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
//@EnableJpaAuditing
public class EssentialGuideApplication {

    public static void main(String[] args) {
        SpringApplication.run(EssentialGuideApplication.class, args);
    }

}
