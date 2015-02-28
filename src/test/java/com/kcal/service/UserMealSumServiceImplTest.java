package com.kcal.service;

import com.kcal.model.Food;
import com.kcal.model.UserMeal;
import com.kcal.model.UserMealSum;
import com.kcal.model.builder.FoodBuilder;
import com.kcal.utils.BuilderTest;
import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserMealSumServiceImplTest {


    @Mock
    private UserMealService userMealService;

    
    private UserMealSumServiceImpl service;
   
    @Before
    public void init(){
        service = new UserMealSumServiceImpl(userMealService);
    }
    
    @Test
      public void shouldReturnCorrectSum(){

        UserMeal userMeal = BuilderTest.createUserMeal(1,createFood(1,"a"),createFood(2,"b"),createFood(3,"c"));
        UserMealSum userMealSum = service.getUserMealSum(userMeal);

        Assertions.assertThat(userMealSum.getProtein()).isEqualTo(30);
        Assertions.assertThat(userMealSum.getCarbohydrate()).isEqualTo(30);
        Assertions.assertThat(userMealSum.getFat()).isEqualTo(30);
        Assertions.assertThat(userMealSum.getKcal()).isEqualTo(600);
        Assertions.assertThat(userMealSum.getWeight()).isEqualTo(300);

    }

    @Test
    public void shouldReturnCorrectSumForDefaultMeal(){
        UserMeal userMeal = BuilderTest.createUserMeal(1, createFood(1, "a"), createFood(2, "b"), createFood(3, "c"));
        Mockito.when(userMealService.getDefaultMeal()).thenReturn(userMeal);
        UserMealSum userMealSum = service.getUserMealSum(userMeal);

        Assertions.assertThat(userMealSum.getProtein()).isEqualTo(30);
        Assertions.assertThat(userMealSum.getCarbohydrate()).isEqualTo(30);
        Assertions.assertThat(userMealSum.getFat()).isEqualTo(30);
        Assertions.assertThat(userMealSum.getKcal()).isEqualTo(600);
        Assertions.assertThat(userMealSum.getWeight()).isEqualTo(300);

    }
    


    private Food createFood(long id, String name){
        Food food = new Food();
        food.setId(id);
        food.setName(name);
        return new FoodBuilder(food).protein(10).carbohydrate(10).fat(10).weight(100).kcal(200).build();
    }
}