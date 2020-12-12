package com.ereport.master.kenML.controller;

import com.ereport.master.kenML.service.MaterialCatalogService;
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
@RequestMapping("/api/material/catalog")
@Api(tags = "Страница ФИЛЬТР MaterialCatalog")
public class MaterialCatalogController extends BaseController {

    private final MaterialCatalogService materialCatalogService;

    @GetMapping("/otdel")
    @ApiOperation("Получение всех отделов")
    public ResponseEntity<?> getAllOtdels() {
        return buildResponse(this.materialCatalogService.getAllOtdels(), HttpStatus.OK);
    }

    @GetMapping("/otdel/sub")
    @ApiOperation("Получение всех под отделов")
    public ResponseEntity<?> getAllSubByOwner(@RequestParam Long mcOwner) {
        return buildResponse(this.materialCatalogService.getAllSubByOwner(mcOwner), HttpStatus.OK);
    }
}
