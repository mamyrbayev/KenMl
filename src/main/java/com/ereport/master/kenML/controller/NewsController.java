package com.ereport.master.kenML.controller;

import com.ereport.master.kenML.domain.dto.*;
import com.ereport.master.kenML.service.ReportGenerationService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(path = "/view")
public class NewsController {
    private final ReportGenerationService reportGenerationService;

    public NewsController(ReportGenerationService reportGenerationService) {
        this.reportGenerationService = reportGenerationService;
    }

    @GetMapping("/pdf")
    public ModelAndView news(){
        ReportGenerationResponse reportGenerationResponse = reportGenerationService.getResponse();

        List<MaterialDTO> materialDTOS = reportGenerationResponse.getTopTen();
        List<OverallForYear> overallForYears = reportGenerationResponse.getOverallForYears();
        List<OverallForYearByRegion> overallForYearByRegions = reportGenerationResponse.getOverallForYearByRegions();
        List<Potrebnosti> potrebnostis = reportGenerationResponse.getPotrebnostis();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("topTenList", materialDTOS);
        modelAndView.addObject("overallForYears", overallForYears);
        modelAndView.addObject("overallForYearByRegions", overallForYearByRegions);
        modelAndView.addObject("potrebnostis", potrebnostis);
        modelAndView.setViewName("stroymart");
        return modelAndView;
    }

}
