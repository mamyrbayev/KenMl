package com.ereport.master.kenML.controller;

import com.ereport.master.kenML.domain.dto.*;
import com.ereport.master.kenML.service.ReportGenerationService;
import com.ereport.master.kenML.service.ResourcesService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping(path = "/view")
public class NewsController {
    private final ReportGenerationService reportGenerationService;
    private final ResourcesService resourcesService;

    public NewsController(ReportGenerationService reportGenerationService, ResourcesService resourcesService) {
        this.reportGenerationService = reportGenerationService;
        this.resourcesService = resourcesService;
    }

    @GetMapping("/pdf")
    public ModelAndView news(HttpServletRequest request) throws ParseException {
        String domainName = request.getRemoteAddr();
        ReportGenerationResponse reportGenerationResponse = reportGenerationService.getResponse();

        String siteUrl = "https://test.stroimaterial.kz";

        List<MaterialDTO> materialDTOS = reportGenerationResponse.getTopTen();
        List<OverallForYear> overallForYears = reportGenerationResponse.getOverallForYears();
        List<OverallForYearByRegion> overallForYearByRegions = reportGenerationResponse.getOverallForYearByRegions();
        List<Potrebnosti> potrebnostis = reportGenerationResponse.getPotrebnostis();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("topTenList", materialDTOS);
        modelAndView.addObject("overallForYears", overallForYears);
        modelAndView.addObject("overallForYearByRegions", overallForYearByRegions);
        modelAndView.addObject("potrebnostis", potrebnostis);
        modelAndView.addObject("siteUrl", siteUrl);
        modelAndView.addObject("domainName", domainName);
        modelAndView.setViewName("index");
        return modelAndView;
    }


    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("storymart/index");
        return modelAndView;
    }


}
