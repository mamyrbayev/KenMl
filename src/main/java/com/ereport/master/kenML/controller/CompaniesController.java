package com.ereport.master.kenML.controller;

import com.ereport.master.controller.BaseController;
import com.ereport.master.kenML.domain.Companies;
import com.ereport.master.kenML.service.CompaniesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/companies")
public class CompaniesController extends BaseController {
    private final CompaniesService companiesService;

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return buildResponse(companiesService.findAll(), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<?> getById(@RequestParam Integer id) {
        return buildResponse(companiesService.findById(id), HttpStatus.OK);
    }


    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Companies companies){
        return buildResponse(companiesService.update(companies), HttpStatus.OK);
    }



}
