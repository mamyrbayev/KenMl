package com.ereport.master.controller;

import com.ereport.master.domain.Category;
import com.ereport.master.domain.Report;
import com.ereport.master.exceptions.ServiceException;
import com.ereport.master.service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports/old")
public class ReportController extends BaseController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> getAll() {
        return buildResponse(reportService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Report report) throws ServiceException {
        return buildResponse(reportService.add(report), HttpStatus.OK);
    }

    @GetMapping("/findId")
    public ResponseEntity<?> getId(@RequestParam Long id) {
        return buildResponse(reportService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Report report) throws ServiceException {
        return buildResponse(reportService.update(report), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam Long id) {
        reportService.delete(id);
        return buildResponse("deleted", HttpStatus.OK);
    }

    @PutMapping("/update/categoryList")
    public ResponseEntity<?> update(@RequestParam Long id, @RequestBody List<Category> categories) throws ServiceException {
        return buildResponse(reportService.setCategoryList(id, categories), HttpStatus.OK);
    }

    @GetMapping("/findAll/V2")
    public ResponseEntity<?> getAllV2() {
        return buildResponse(reportService.findAllToFront(), HttpStatus.OK);
    }

    @GetMapping("/findId/V2")
    public ResponseEntity<?> getIdV2(@RequestParam Long id) {
        return buildResponse(reportService.findByIdToFront(id), HttpStatus.OK);
    }

    @GetMapping("/expiration")
    public ResponseEntity<?> getExpirationTime(@RequestParam Long id){
        return buildResponse(reportService.getExpirationTime(id), HttpStatus.OK);
    }
}
