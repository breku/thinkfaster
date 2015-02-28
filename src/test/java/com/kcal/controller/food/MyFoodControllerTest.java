package com.kcal.controller.food;

import com.kcal.model.json.MyFoodRequestJson;
import com.kcal.service.UserMealService;
import com.kcal.service.database.security.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(MockitoJUnitRunner.class)
public class MyFoodControllerTest {

    private MyFoodController controller;

    @Mock
    private UserService userService;

    @Mock
    private UserMealService userMealService;


    private MockMvc mockMvc;

    @Before
    public void init(){
        controller = new MyFoodController(userService,userMealService);
        mockMvc = MockMvcBuilders.<StandaloneMockMvcBuilder>standaloneSetup(controller).build();
    }


    @Test
    public void shouldReturnOk() throws Exception {
        Mockito.when(userMealService.updateMealNumber(Matchers.any(MyFoodRequestJson.class))).thenReturn(Boolean.TRUE);
        this.mockMvc.perform(post("/user/food/my/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"foodId\": 423, \"target\":\"DEFAULT\",\"sender\":\"MEAL1\" }"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldReturnBadRequest() throws Exception {
        Mockito.when(userMealService.updateMealNumber(Matchers.any(MyFoodRequestJson.class))).thenReturn(Boolean.FALSE);
        this.mockMvc.perform(post("/user/food/my/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"foodId\": 423, \"target\":\"DEFAULT\",\"sender\":\"MEAL1\" }"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void shouldReturnBadRequestForWrongJson() throws Exception {
        Mockito.when(userMealService.updateMealNumber(Matchers.any(MyFoodRequestJson.class))).thenReturn(Boolean.FALSE);
        this.mockMvc.perform(post("/user/food/my/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"foogsdfgdId\": 423, \"starget\":\"DEFAULT\",\"sendefgssfgr\":\"MEAL1\" }"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}