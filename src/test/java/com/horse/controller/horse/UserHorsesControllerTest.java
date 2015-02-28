package com.horse.controller.horse;

import com.horse.dao.HorseDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@RunWith(MockitoJUnitRunner.class)
public class UserHorsesControllerTest {

    private UserHorsesController uut;

    @Mock
    private HorseDao horseDao;

    private MockMvc mockMvc;


    @Before
    public void init(){
        uut = new UserHorsesController(horseDao);
        mockMvc = MockMvcBuilders.<StandaloneMockMvcBuilder>standaloneSetup(uut).build();

    }

    @Test
    public void shouldReturnInitView() throws Exception {
        this.mockMvc.perform(get("/user/horses"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
}