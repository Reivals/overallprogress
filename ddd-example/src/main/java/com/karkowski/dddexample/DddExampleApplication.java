package com.karkowski.dddexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication(exclude = {R2dbcAutoConfiguration.class })
@EnableWebFlux
@EnableR2dbcRepositories
public class DddExampleApplication {

    public static void main(final String[] args) {
        SpringApplication.run(DddExampleApplication.class, args);
    }

}
