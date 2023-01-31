package com.miseat.global.database;

import com.miseat.global.profile.AppProfiles;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.h2.tools.Server;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Objects;

@Configuration
@Profile(AppProfiles.H2)
public class H2DataSourceConfig {
    private Server server;

    @PostConstruct
    public void start() throws SQLException {
        this.server = Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "8082").start();
    }

    @PreDestroy
    public void stop() {
        if (Objects.isNull(this.server)) {
            return;
        }
        server.stop();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.hikari")
    public DataSource dataSource() {
        return new HikariDataSource();
    }
}