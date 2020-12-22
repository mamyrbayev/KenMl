package com.ereport.master.kenML.service;

import com.ereport.master.email.emailHelper.EmailHelper;
import com.ereport.master.kenML.domain.dto.*;


import com.github.jhonnymertz.wkhtmltopdf.wrapper.Pdf;
import com.github.jhonnymertz.wkhtmltopdf.wrapper.configurations.WrapperConfig;
import com.github.jhonnymertz.wkhtmltopdf.wrapper.params.Param;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class HtmlToPdfService {

    @Autowired
    private EmailHelper helper;

    @Autowired
    private ReportGenerationService reportGenerationService;

    @Value("${system.file_locations}")
    private String location;

    public String parseThymeleafTemplate() throws IOException {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();

        templateResolver.setPrefix("/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setTemplateMode(TemplateMode.CSS);
        templateResolver.setTemplateMode(TemplateMode.JAVASCRIPT);
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setOrder(1);
        templateResolver.setCheckExistence(true);

        ClassLoader classLoader = getClass().getClassLoader();

        ReportGenerationResponse reportGenerationResponse = reportGenerationService.getResponse();

        List<MaterialDTO> materialDTOS = reportGenerationResponse.getTopTen();
        List<OverallForYear> overallForYears = reportGenerationResponse.getOverallForYears();
        List<OverallForYearByRegion> overallForYearByRegions = reportGenerationResponse.getOverallForYearByRegions();
        List<Potrebnosti> potrebnostis = reportGenerationResponse.getPotrebnostis();
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);


        System.out.println("topTenResponses " + materialDTOS);
        System.out.println("overallForYears " + overallForYears);
        System.out.println("overallForYearByRegions " + overallForYearByRegions);

        Context context = new Context();
        context.setVariable("topTenList", materialDTOS);
        context.setVariable("overallForYears", overallForYears);
        context.setVariable("overallForYearByRegions", overallForYearByRegions);
        context.setVariable("potrebnostis", potrebnostis);

        return templateEngine.process("stroymart", context);
    }


    public String parseThymeleafFooter() throws IOException {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();

        templateResolver.setPrefix("/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setTemplateMode(TemplateMode.CSS);
        templateResolver.setTemplateMode(TemplateMode.JAVASCRIPT);
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setOrder(1);
        templateResolver.setCheckExistence(true);

        ClassLoader classLoader = getClass().getClassLoader();

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);


        return templateEngine.process("footer", new Context());
    }



    public String generate() throws InterruptedException, IOException {
        SimpleDateFormat sdfTime = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
        ClassLoader classLoader = getClass().getClassLoader();

        Pdf pdf = new Pdf();
        pdf.addParam(new Param("--footer-html", "/Users/user/Downloads/eReport/src/main/resources/templates/footer.html"));
        pdf.addPageFromString(parseThymeleafTemplate());
        pdf.addParam(new Param("--page-size", "A4", "-B", "20mm", "-L", "0", "-R", "0", "-T", "0"));

        pdf.addParam(new Param("--javascript-delay", "3000"));

        String pdfName = "pdf-" + sdfTime.format(new Date()) + ".pdf";
        pdf.saveAs(this.location + pdfName);

        return pdfName;
    }






}
