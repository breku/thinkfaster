package com.horse;

import com.horse.configuration.TestConfig;
import com.horse.configuration.TestDatabaseConfig;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by brekol on 05.12.14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ TestConfig.class, TestDatabaseConfig.class })
public class AbstractSpringTestConfiguration {
}
