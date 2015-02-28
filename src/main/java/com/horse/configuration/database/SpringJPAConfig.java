package com.horse.configuration.database;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by brekol on 26.11.14.
 */
@Configuration
@EnableJpaRepositories(basePackages = {"com.horse.dao"})
@EnableTransactionManagement
public class SpringJPAConfig {
}
