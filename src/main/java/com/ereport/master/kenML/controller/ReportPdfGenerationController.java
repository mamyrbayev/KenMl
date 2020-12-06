package com.ereport.master.kenML.controller;

import com.ereport.master.controller.BaseController;
import com.ereport.master.kenML.service.LocalitiesService;
import com.ereport.master.kenML.service.ObjectService;
import com.ereport.master.kenML.service.ReportGenerationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/pdf")
public class ReportPdfGenerationController extends BaseController {
    private final ReportGenerationService reportGenerationService;
    private final ObjectService objectService;

    @GetMapping
    public ResponseEntity<?> getResponse() {
        return buildResponse(this.reportGenerationService.getResponse(), HttpStatus.OK);
    }

    @GetMapping("/topTen")
    public ResponseEntity<?> getResponseTopTen() {
        return buildResponse(this.reportGenerationService.getTopTen(), HttpStatus.OK);
    }

    @GetMapping("/overall/years")
    public ResponseEntity<?> getOverallForYear() {
        return buildResponse(this.objectService.getOverallForYear(), HttpStatus.OK);
    }
}
