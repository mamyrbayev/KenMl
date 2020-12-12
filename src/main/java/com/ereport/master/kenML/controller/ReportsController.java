package com.ereport.master.kenML.controller;

import com.ereport.master.controller.BaseController;
import com.ereport.master.exceptions.ServiceException;
import com.ereport.master.kenML.domain.Reports;
import com.ereport.master.kenML.service.ReportsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/reports")
public class ReportsController extends BaseController {
    private final ReportsService reportsService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Reports reports){
        return buildResponse(reportsService.save(reports), HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return buildResponse(reportsService.findAll(), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<?> getById(@RequestParam Integer id) {
        return buildResponse(reportsService.findById(id), HttpStatus.OK);
    }


    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Reports reports){
        return buildResponse(reportsService.update(reports), HttpStatus.OK);
    }



}
