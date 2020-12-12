package com.ereport.master.kenML.controller;

import com.ereport.master.controller.BaseController;
import com.ereport.master.domain.MaterialList;
import com.ereport.master.exceptions.ServiceException;
import com.ereport.master.kenML.domain.Categories;
import com.ereport.master.kenML.domain.ReportMaterials;
import com.ereport.master.kenML.service.CategoriesService;
import com.ereport.master.kenML.service.ReportMaterialsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/materialList")
public class ReportMaterialsController extends BaseController {
    private final ReportMaterialsService reportMaterialsService;

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return buildResponse(reportMaterialsService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/useFilter")
    public ResponseEntity<?> useFilter(@RequestBody List<ReportMaterials> reportMaterialsList) throws ServiceException {
        return buildResponse(reportMaterialsService.useFilter(reportMaterialsList), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> useFilter(@RequestBody ReportMaterials reportMaterials) throws ServiceException {
        return buildResponse(reportMaterialsService.save(reportMaterials), HttpStatus.OK);
    }
}
