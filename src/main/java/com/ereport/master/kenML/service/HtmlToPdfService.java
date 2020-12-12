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

//        BufferedReader br = new BufferedReader(
//                new FileReader("json/response_1607324469558.json"));
        ClassLoader classLoader = getClass().getClassLoader();

        BufferedReader br = new BufferedReader(new FileReader(Objects.requireNonNull(classLoader.getResource("json/response_1607324469558.json")).getFile()));
        ReportGenerationResponse reportGenerationResponse = new Gson().fromJson(br, ReportGenerationResponse.class);

        Gson g = new Gson();
//        ReportGenerationResponse reportGenerationResponse = g.fromJson(report, ReportGenerationResponse.class);

//        List<TopTenResponse> topTenResponses = new ArrayList<>();

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



    public String generate() throws InterruptedException, IOException {
        SimpleDateFormat sdfTime = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
        ClassLoader classLoader = getClass().getClassLoader();

//        Pdf pdf = new Pdf(new WrapperConfig("C:\\Program Files\\wkhtmltopdf\\bin\\wkhtmltopdf.exe"));
        Pdf pdf = new Pdf();
//        Pdf pdf = new Pdf(new WrapperConfig("/usr/local/bin/wkhtmltopdf"));
        pdf.addPageFromString(parseThymeleafTemplate());
        pdf.addParam(new Param("--page-size", "A4", "-B", "0", "-L", "0", "-R", "0", "-T", "0"));

        pdf.addParam(new Param("--javascript-delay", "3000"));

        String pdfName = "pdf-" + sdfTime.format(new Date()) + ".pdf";
        pdf.saveAs(this.location + pdfName);

        return pdfName;
    }






}
