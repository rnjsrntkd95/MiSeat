package com.miseat;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(
        exclude = DataSourceAutoConfiguration.class    // TODO: DataSource 추가 후 제거
)
@EnableJpaAuditing
public class MiSeatApplication {

    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(MiSeatApplication.class, args);
    }

}
