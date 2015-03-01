package com.kcal;

import com.kcal.aspect.DaoAspect;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by brekol on 01.03.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ DaoAspect.class})
public class AbstractSpringTestConfiguration {
}
