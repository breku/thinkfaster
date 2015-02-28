package com.horse.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by brekol on 05.12.14.
 */

@Configuration
@ComponentScan(basePackages = {
        "com.horse.dao",
       "com.horse.model"
})
public class TestConfig {
}
