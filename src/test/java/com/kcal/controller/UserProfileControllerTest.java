package com.kcal.controller;

import com.kcal.controller.food.MyFoodController;
import com.kcal.model.json.MyFoodRequestJson;
import com.kcal.service.UserMealService;
import com.kcal.service.database.security.UserService;
import com.kcal.utils.BuilderTest;
import com.kcal.validator.UserProfileValidator;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(JUnitParamsRunner.class)
public class UserProfileControllerTest {



    private UserProfileController controller;

    @Mock
    private UserService userService;


    private UserProfileValidator validator =  new UserProfileValidator();


    private MockMvc mockMvc;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        controller = new UserProfileController(userService,validator);
        mockMvc = MockMvcBuilders.<StandaloneMockMvcBuilder>standaloneSetup(controller).build();
        Mockito.when(userService.getUserProfile()).thenReturn(BuilderTest.createProfile(40,40,20));

    }

    @Test
    @Parameters({"20,20,60",
            "100,0,0",
            "1,1,98",
            "0,0,100",
            "50,50,0"})
    public void shouldReturnOk(String proteinPercent, String carbohydratePercent, String fatPercent) throws Exception {
        this.mockMvc.perform(post("/user/profile/edit/slider")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"protein\": 3, \"carbohydrate\": 50,\"fat\": 0,  " +
                        "\"proteinPercent\": "+ proteinPercent+ ", \"carbohydratePercent\": " +carbohydratePercent +",\"fatPercent\": "+ fatPercent+"}"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @Parameters({"20,2,60",
            "100,0,1",
            "x,0,100",
            "2,11,123123",
            "0,0,0",
            "0,0,1",
            "0,0,99"})
    public void shouldReturnBadRequest(String proteinPercent, String carbohydratePercent, String fatPercent) throws Exception {
        this.mockMvc.perform(post("/user/profile/edit/slider")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"protein\": 3, \"carbohydrate\": 50,\"fat\": 0,  " +
                        "\"proteinPercent\": "+ proteinPercent+ ", \"carbohydratePercent\": " +carbohydratePercent +",\"fatPercent\": "+ fatPercent+"}"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}