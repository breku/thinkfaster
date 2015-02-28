package com.kcal.service.database;

import com.kcal.dao.UserMealDao;
import com.kcal.model.Food;
import com.kcal.model.User;
import com.kcal.model.UserMeal;
import com.kcal.model.json.FoodStatus;
import com.kcal.model.utils.MealNumber;
import com.kcal.service.UserMealService;
import com.kcal.service.database.security.UserService;
import com.kcal.utils.BuilderTest;
import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class UserMealServiceImplTest {


    UserMealService userMealService;

    @Mock
    private UserService userService;

    @Mock
    private FoodService foodService;

    @Mock
    private UserMealDao userMealDao;

    @Before
    public void init(){
        userMealService = new UserMealServiceImpl(userMealDao,userService,foodService);
    }

    @Test
    public void shouldReturnDefaultMealForAlreadyExisting(){
        // given
        User user = BuilderTest.createUser(1, "kuba");
        Mockito.when(userService.getCurrentUser()).thenReturn(user);
        UserMeal defaultUserMeal = BuilderTest.createUserMeal(1);
        Mockito.when(userMealDao.findMealByNameAndUser(Matchers.anyString(),Matchers.eq(user))).thenReturn(defaultUserMeal);

        // when
        UserMeal defaultMeal = userMealService.getDefaultMeal();

        // then
        Assertions.assertThat(defaultMeal).isEqualTo(defaultMeal);
    }


    @Test
    public void shouldReturnDefaultMealForNonExisting(){
        // given
        User user = BuilderTest.createUser(1, "kuba");
        Mockito.when(userService.getCurrentUser()).thenReturn(user);
        Mockito.when(userMealDao.findMealByNameAndUser(Matchers.anyString(),Matchers.eq(user))).thenReturn(null);

        // when
        UserMeal defaultMeal = userMealService.getDefaultMeal();

        // then
        Assertions.assertThat(defaultMeal).isNotNull();
        Assertions.assertThat(defaultMeal.getUser()).isEqualTo(user);
    }


    @Test
    public void shouldUpdateFoodInUserMealAdding(){

        Food foodA = BuilderTest.createFood(1, "a");
        Food foodB = BuilderTest.createFood(2, "b");
        Food foodC = BuilderTest.createFood(3, "c");
        Mockito.when(foodService.get(1)).thenReturn(foodA);
        Mockito.when(foodService.get(2)).thenReturn(foodB);
        Mockito.when(foodService.get(3)).thenReturn(foodC);

        UserMeal userMeal = BuilderTest.createUserMeal(1, foodA, foodB);

        FoodStatus foodStatus = userMealService.addFoodToUserMeal(userMeal, BuilderTest.createFoodRequestJson(1, 1));

        Assertions.assertThat(foodStatus).isEqualTo(FoodStatus.ADD_UPDATE);
        Assertions.assertThat(userMeal.getFoodMap().get(foodA.getId()).getCarbohydrate()).isEqualTo(20);
        Assertions.assertThat(userMeal.getFoodMap().get(foodA.getId()).getFat()).isEqualTo(20);
        Assertions.assertThat(userMeal.getFoodMap().get(foodA.getId()).getKcal()).isEqualTo(20);
        Assertions.assertThat(userMeal.getFoodMap().get(foodA.getId()).getProtein()).isEqualTo(20);
        Assertions.assertThat(userMeal.getFoodMap().get(foodA.getId()).getWeight()).isEqualTo(110);


        foodStatus = userMealService.addFoodToUserMeal(userMeal, BuilderTest.createFoodRequestJson(1, 0.5));
        Assertions.assertThat(foodStatus).isEqualTo(FoodStatus.ADD_UPDATE);
        Assertions.assertThat(userMeal.getFoodMap().get(foodA.getId()).getCarbohydrate()).isEqualTo(25);
        Assertions.assertThat(userMeal.getFoodMap().get(foodA.getId()).getFat()).isEqualTo(25);
        Assertions.assertThat(userMeal.getFoodMap().get(foodA.getId()).getKcal()).isEqualTo(25);
        Assertions.assertThat(userMeal.getFoodMap().get(foodA.getId()).getProtein()).isEqualTo(25);
        Assertions.assertThat(userMeal.getFoodMap().get(foodA.getId()).getWeight()).isEqualTo(160);
    }


    @Test
    public void shouldAddFoodToUserMeal(){

        Food foodA = BuilderTest.createFood(1, "a");
        Food foodB = BuilderTest.createFood(2, "b");
        Food foodC = BuilderTest.createFood(3, "c");
        Mockito.when(foodService.get(1)).thenReturn(foodA);
        Mockito.when(foodService.get(2)).thenReturn(foodB);
        Mockito.when(foodService.get(3)).thenReturn(foodC);

        UserMeal userMeal = BuilderTest.createUserMeal(1, foodA, foodB);

        FoodStatus foodStatus = userMealService.addFoodToUserMeal(userMeal, BuilderTest.createFoodRequestJson(3, 1));

        Assertions.assertThat(foodStatus).isEqualTo(FoodStatus.ADD_ELEMENT);
        Assertions.assertThat(userMeal.getFoodMap().get(foodC.getId()).getCarbohydrate()).isEqualTo(10);
        Assertions.assertThat(userMeal.getFoodMap().get(foodC.getId()).getFat()).isEqualTo(10);
        Assertions.assertThat(userMeal.getFoodMap().get(foodC.getId()).getKcal()).isEqualTo(10);
        Assertions.assertThat(userMeal.getFoodMap().get(foodC.getId()).getProtein()).isEqualTo(10);
        Assertions.assertThat(userMeal.getFoodMap().get(foodC.getId()).getWeight()).isEqualTo(10);

        foodStatus = userMealService.addFoodToUserMeal(userMeal, BuilderTest.createFoodRequestJson(3, 0.5));
        Assertions.assertThat(foodStatus).isEqualTo(FoodStatus.ADD_UPDATE);
        Assertions.assertThat(userMeal.getFoodMap().get(foodC.getId()).getCarbohydrate()).isEqualTo(15);
        Assertions.assertThat(userMeal.getFoodMap().get(foodC.getId()).getFat()).isEqualTo(15);
        Assertions.assertThat(userMeal.getFoodMap().get(foodC.getId()).getKcal()).isEqualTo(15);
        Assertions.assertThat(userMeal.getFoodMap().get(foodC.getId()).getProtein()).isEqualTo(15);
        Assertions.assertThat(userMeal.getFoodMap().get(foodC.getId()).getWeight()).isEqualTo(60);

    }


    @Test
    public void shouldRemoveFoodFromUserMeal(){
        Food foodA = BuilderTest.createFood(1, "a");
        Food foodB = BuilderTest.createFood(2, "b");
        Food foodC = BuilderTest.createFood(3, "c");
        Mockito.when(foodService.get(1)).thenReturn(foodA);
        Mockito.when(foodService.get(2)).thenReturn(foodB);
        Mockito.when(foodService.get(3)).thenReturn(foodC);

        UserMeal userMeal = BuilderTest.createUserMeal(1, foodA, foodB, foodC);

        FoodStatus foodStatus = userMealService.removeFoodFromUserMeal(userMeal, BuilderTest.createFoodRequestJson(3, 1));

        Assertions.assertThat(foodStatus).isEqualTo(FoodStatus.REMOVE_ELEMENT);
        Assertions.assertThat(userMeal.getFoodMap().get(foodC.getId())).isNull();

    }


    @Test
    public void shouldUpdateFoodInUserMealRemoving(){
        Food foodA = BuilderTest.createFood(1, "a");
        Food foodB = BuilderTest.createFood(2, "b");
        Food foodC = BuilderTest.createFood(3, "c");
        Mockito.when(foodService.get(1)).thenReturn(foodA);
        Mockito.when(foodService.get(2)).thenReturn(foodB);
        Mockito.when(foodService.get(3)).thenReturn(foodC);

        UserMeal userMeal = BuilderTest.createUserMeal(1, foodA, foodB, foodC);

        FoodStatus foodStatus = userMealService.removeFoodFromUserMeal(userMeal, BuilderTest.createFoodRequestJson(3, 0.05));

        Assertions.assertThat(foodStatus).isEqualTo(FoodStatus.REMOVE_UPDATE);
        Assertions.assertThat(userMeal.getFoodMap().get(foodC.getId()).getCarbohydrate()).isEqualTo(9.5);
        Assertions.assertThat(userMeal.getFoodMap().get(foodC.getId()).getFat()).isEqualTo(9.5);
        Assertions.assertThat(userMeal.getFoodMap().get(foodC.getId()).getKcal()).isEqualTo(9.5);
        Assertions.assertThat(userMeal.getFoodMap().get(foodC.getId()).getProtein()).isEqualTo(9.5);
        Assertions.assertThat(userMeal.getFoodMap().get(foodC.getId()).getWeight()).isEqualTo(5);


    }

    @Test
    public void shouldUpdateMealNumber(){

        //given
        User user = BuilderTest.createUser(1, "kuba");
        Mockito.when(userService.getCurrentUser()).thenReturn(user);
        UserMeal defaultUserMeal = BuilderTest.createUserMeal(1, BuilderTest.createFood(1, "kurczak"));
        Mockito.when(userMealDao.findMealByNameAndUser(Matchers.anyString(),Matchers.eq(user))).thenReturn(defaultUserMeal);

        //when
        boolean b = userMealService.updateMealNumber(BuilderTest.createMyFoodRequestJson(1, MealNumber.DEFAULT, MealNumber.MEAL1));

        // then
        Assertions.assertThat(b).isTrue();
    }

    @Test
    public void shouldNotUpdateMealNumber(){

        //given
        User user = BuilderTest.createUser(1, "kuba");
        Mockito.when(userService.getCurrentUser()).thenReturn(user);
        UserMeal defaultUserMeal = BuilderTest.createUserMeal(1);
        Mockito.when(userMealDao.findMealByNameAndUser(Matchers.anyString(),Matchers.eq(user))).thenReturn(defaultUserMeal);

        //when
        boolean b = userMealService.updateMealNumber(BuilderTest.createMyFoodRequestJson(1, MealNumber.DEFAULT, MealNumber.MEAL1));

        // then
        Assertions.assertThat(b).isFalse();
    }

    @Test
    public void shouldReturnCorrectFoodMapFromUserMeal(){

        //given
        User user = BuilderTest.createUser(1, "kuba");
        Mockito.when(userService.getCurrentUser()).thenReturn(user);
        UserMeal defaultUserMeal = BuilderTest.createUserMeal(1,BuilderTest.createFood(1,"a",MealNumber.MEAL1), BuilderTest.createFood(2,"a",MealNumber.MEAL0), BuilderTest.createFood(2,"a",MealNumber.DEFAULT));
        Mockito.when(userMealDao.findMealByNameAndUser(Matchers.anyString(),Matchers.eq(user))).thenReturn(defaultUserMeal);

        Map<Long, Food> map = userMealService.getFoodMapFromDefaultMeal(MealNumber.MEAL1);

        Assertions.assertThat(map).hasSize(1);
        Assertions.assertThat(map.get((long)1).getName()).isEqualTo("a");

    }










}