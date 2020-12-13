package com.ereport.master.kenML.controller;

import com.ereport.master.kenML.domain.Categories;
import com.ereport.master.kenML.service.CategoriesService;
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


    @GetMapping("/report")
    public ResponseEntity<?> getByReportId(@RequestParam Integer id) {
        return buildResponse(categoriesService.findAllByReportId(id), HttpStatus.OK);
    }

    @GetMapping("/report/v2")
    public ResponseEntity<?> getByReportIdV2(@RequestParam Integer id) {
        return buildResponse(categoriesService.findAllByReportIdV2(id), HttpStatus.OK);
    }



    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteById(@RequestParam Integer id) {
        categoriesService.deleteCategory(id);
        return buildResponse("deleted", HttpStatus.OK);
    }


}
