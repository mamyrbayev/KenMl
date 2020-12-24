package com.ereport.master.kenML.controller;

import com.ereport.master.kenML.service.HtmlToPdfService;
import com.ereport.master.kenML.service.PublicationsService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.FileInputStream;
import java.io.IOException;

@Controller
@RequestMapping("/api/pdf")
public class GenerationController extends BaseController {

    private final HtmlToPdfService htmlToPdfService;
    private final PublicationsService publicationsService;


    @Value("${system.file_locations}")
    private String location;

    public GenerationController(HtmlToPdfService htmlToPdfService, PublicationsService publicationsService) {
        this.htmlToPdfService = htmlToPdfService;
        this.publicationsService = publicationsService;
    }

    @GetMapping("/generate")
    public ResponseEntity<?> generatePDF() throws Exception {
        return new ResponseEntity<>(htmlToPdfService.generate(), HttpStatus.OK);

    }

    @GetMapping("/view")
    public ModelAndView getView() {

        return new ModelAndView("index");
    }

    @GetMapping(value = "/{photo:.+}", produces = {MediaType.APPLICATION_PDF_VALUE})
    @ResponseBody
    public byte[] showPdf(@PathVariable String photo) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(this.location + "/" + photo)) {
            return IOUtils.toByteArray(inputStream);
        } catch (Exception e) {
            FileInputStream inputStream = new FileInputStream("");
            return IOUtils.toByteArray(inputStream);
        }
    }
}
