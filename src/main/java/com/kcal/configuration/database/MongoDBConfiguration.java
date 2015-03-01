package com.kcal.configuration.database;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * User: Breku
 * Date: 02.07.14
 */

@Configuration
public class MongoDBConfiguration {


    @Value("${database.name}")
    private String databaseName;

    @Value("${database.host}")
    private String host;

    @Value("${database.username}")
    private String username;

    @Value("${database.password}")
    private String password;

    @Value("${database.port}")
    private int port;

}
