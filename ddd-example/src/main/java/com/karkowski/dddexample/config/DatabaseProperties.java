package com.karkowski.dddexample.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class DatabaseProperties {

    private final String hostName;
    private final String databaseName;
    private final String userName;
    private final String password;
    private final Integer port;

    public DatabaseProperties(@Value("${db.hostName}") final String hostName,
                              @Value("${db.databaseName}") final String databaseName,
                              @Value("${db.userName}") final String userName,
                              @Value("${db.password}") final String password,
                              @Value("${db.port}") final Integer port) {
        this.hostName = hostName;
        this.databaseName = databaseName;
        this.userName = userName;
        this.password = password;
        this.port = port;
    }
}
