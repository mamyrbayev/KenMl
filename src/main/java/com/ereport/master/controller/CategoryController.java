package com.ereport.master.controller;

import com.ereport.master.domain.Category;
import com.ereport.master.domain.dto.CategoryContractorsRequest;
import com.ereport.master.exceptions.ServiceException;
import com.ereport.master.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryController extends BaseController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService= categoryService ;
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> getAll() {
        return buildResponse(categoryService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Category category) throws ServiceException {
        return buildResponse(categoryService.add( category), HttpStatus.OK);
    }

    @PostMapping("/add/with/contractors")
    public ResponseEntity<?> add(@RequestBody CategoryContractorsRequest category) throws ServiceException {
        return buildResponse(categoryService.addCategoriesWithContractors(category), HttpStatus.OK);
    }

    @GetMapping("/findId")
    public ResponseEntity<?> getId(@RequestParam Long id) {
        return buildResponse(categoryService.findId(id), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Category category) throws ServiceException {
        return buildResponse(categoryService.update(category), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam Long id) {
        categoryService.delete(id);
        return buildResponse("deleted", HttpStatus.OK);
    }

    @GetMapping("/report")
    public ResponseEntity<?> getByReportId(@RequestParam Long id) {
        return buildResponse(categoryService.findAllByReportId(id), HttpStatus.OK);
    }

    @GetMapping("/report/v2")
    public ResponseEntity<?> getByReportIdV2(@RequestParam Long id) {
        return buildResponse(categoryService.findAllByReportIdV2(id), HttpStatus.OK);
    }
}
