package com.kcal.service.excel;

import com.kcal.model.CountType;
import com.kcal.model.Food;
import com.kcal.model.utils.MealNumber;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Breku
 * Date: 2014-09-24
 */
@Service
public class ExcelService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelService.class);


    private static final int COLUMN_NAME = 1;
    private static final int COLUMN_NAME_ANG = 2;
    private static final int COLUMN_WEIGHT_ONE_PIECE_NAME = 3;
    private static final int COLUMN_WEIGHT_ONE_PIECE_NAME_ANG = 4;
    private static final int COLUMN_WEIGHT_ONE_PIECE = 5;
    private static final int COLUMN_WEIGHT = 6;
    private static final int COLUMN_PROTEINS = 7;
    private static final int COLUMN_CARBOHYDRATES = 8;
    private static final int COLUMN_FAT = 9;
    private static final int COLUMN_KCAL = 10;


    public List<Food> retrieveFoodList(InputStream inputStream) throws IOException {
        List<Food> result = new ArrayList<>();
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet ws = wb.getSheetAt(0);
        LOGGER.info(">> Retreived sheet {}", ws);
        LOGGER.info(">> FirstRowNum={} LastRowNum={}", ws.getFirstRowNum(), ws.getLastRowNum());

        for (int i = ws.getFirstRowNum() + 1; i < ws.getLastRowNum() + 1; i++) {
            XSSFRow row = ws.getRow(i);
            Food food = new Food();
            food.setName(row.getCell(COLUMN_NAME).getStringCellValue());
            food.setNameAng(row.getCell(COLUMN_NAME_ANG).getStringCellValue());
            CountType countType = new CountType();
            countType.setName(row.getCell(COLUMN_WEIGHT_ONE_PIECE_NAME).getStringCellValue());
            countType.setNameAng(row.getCell(COLUMN_WEIGHT_ONE_PIECE_NAME_ANG).getStringCellValue());
            countType.setWeight(Double.parseDouble(row.getCell(COLUMN_WEIGHT_ONE_PIECE).getRawValue()));
            food.setCountType(countType);
            food.setWeight(Integer.parseInt(row.getCell(COLUMN_WEIGHT).getRawValue()));
            food.setProtein(Double.parseDouble(row.getCell(COLUMN_PROTEINS).getRawValue()));
            food.setCarbohydrate(Double.parseDouble(row.getCell(COLUMN_CARBOHYDRATES).getRawValue()));
            food.setFat(Double.parseDouble(row.getCell(COLUMN_FAT).getRawValue()));
            food.setKcal(Integer.parseInt(row.getCell(COLUMN_KCAL).getRawValue()));
            food.setMealNumber(MealNumber.DEFAULT);
            result.add(food);
        }
        LOGGER.info("<< Number of food elements {}", result.size());
        return result;
    }
}
