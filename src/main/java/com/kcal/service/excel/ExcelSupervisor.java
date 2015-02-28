package com.kcal.service.excel;

import com.kcal.model.Food;
import com.kcal.service.UserMealService;
import com.kcal.service.database.FoodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * User: Breku
 * Date: 2014-09-27
 */
@Service
public class ExcelSupervisor {

    private ExcelService excelService;

    private FoodService foodService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelSupervisor.class);


    private UserMealService userMealService;

    @Autowired
    public ExcelSupervisor(ExcelService excelService, FoodService foodService, UserMealService userMealService) {
        this.excelService = excelService;
        this.foodService = foodService;
        this.userMealService = userMealService;
    }

    public void processExcelFile(MultipartFile file) throws IOException {
        LOGGER.info(">> Start processing the file {}", file.getName());
        LOGGER.info(">> Retrieving food from excel...");
        List<Food> foodList = excelService.retrieveFoodList(file.getInputStream());
        LOGGER.debug(">> Retrieved food list {}", foodList);
        LOGGER.info(">> Food from excel has been retrieved");
        LOGGER.info(">> Removing food from database...");
        foodService.removeAll();
        LOGGER.info(">> All food from database has been removed");
        LOGGER.info(">> Saving food from excel to db...");
        foodService.save(foodList);
        LOGGER.info(">> Food from excel has been saved");
        LOGGER.info(">> Updating userMeal ID's...");
        userMealService.updateUserMealIds();
        LOGGER.info(">> UserMeal ID's have been updated");
        LOGGER.info("<< File processed and saved correctly");
    }
}
