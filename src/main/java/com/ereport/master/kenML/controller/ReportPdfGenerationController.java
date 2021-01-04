package com.ereport.master.kenML.controller;

import com.ereport.master.kenML.service.ObjectService;
import com.ereport.master.kenML.service.ReportGenerationService;
import com.ereport.master.kenML.service.ResourcesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

import static com.ereport.master.kenML.util.StringUtil.formatNumber;

@RestController
@AllArgsConstructor
@RequestMapping("/api/pdf")
public class ReportPdfGenerationController extends BaseController {
    private final ReportGenerationService reportGenerationService;
    private final ObjectService objectService;
    private final ResourcesService resourcesService;

    @GetMapping
    public ResponseEntity<?> getResponse() throws ParseException {
        return buildResponse(this.reportGenerationService.getResponse(), HttpStatus.OK);
    }

    @GetMapping("/topTen")
    public ResponseEntity<?> getResponseTopTen() throws ParseException {
        return buildResponse(this.reportGenerationService.getTopTen(), HttpStatus.OK);
    }

    @GetMapping("/overall/years")
    public ResponseEntity<?> getOverallForYear() {
        return buildResponse(this.objectService.getOverallForYear(), HttpStatus.OK);
    }

    @GetMapping("/rounding")
    public ResponseEntity<?> rounding(Float num) throws ParseException {
        return buildResponse(formatNumber(num), HttpStatus.OK);
    }
}
