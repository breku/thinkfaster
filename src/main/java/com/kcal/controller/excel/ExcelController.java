package com.kcal.controller.excel;

import com.kcal.service.excel.ExcelSupervisor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * User: Breku
 * Date: 2014-09-24
 */
@Controller
public class ExcelController {

    private ExcelSupervisor excelSupervisor;

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelController.class);

    @Autowired
    public ExcelController(ExcelSupervisor excelSupervisor) {
        this.excelSupervisor = excelSupervisor;
    }

    @RequestMapping(value = "/admin/settings/excel/upload", method = RequestMethod.POST)
    public ModelAndView singleSave(@RequestParam("file") MultipartFile file) {
        try {
            excelSupervisor.processExcelFile(file);
        } catch (IOException e) {
            LOGGER.error("Error processing the file", e);
        }
        return new ModelAndView("redirect:/admin/settings");
    }
}
