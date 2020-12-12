package com.ereport.master.kenML.controller;


import com.ereport.master.controller.BaseController;
import com.ereport.master.kenML.domain.Categories;
import com.ereport.master.kenML.domain.Reports;
import com.ereport.master.kenML.service.CategoriesService;
import com.ereport.master.kenML.service.ReportsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/categories")
public class CategoriesController extends BaseController {

    private final CategoriesService categoriesService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Categories categories){
        return buildResponse(categoriesService.save(categories), HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return buildResponse(categoriesService.findAll(), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<?> getById(@RequestParam Integer id) {
        return buildResponse(categoriesService.findById(id), HttpStatus.OK);
    }


    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Categories categories){
        return buildResponse(categoriesService.update(categories), HttpStatus.OK);
    }




}
