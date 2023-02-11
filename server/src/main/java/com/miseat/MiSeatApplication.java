package com.miseat;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
public class MiSeatApplication {

    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(MiSeatApplication.class, args);
    }

}
