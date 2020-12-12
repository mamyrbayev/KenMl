package com.ereport.master.kenML.controller;

import com.ereport.master.kenML.service.MaterialService;
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
@RequestMapping("/api/material")
@Api(tags = "Страница ФИЛЬТР Material")
public class MaterialController extends BaseController {

    private final MaterialService materialService;


    @GetMapping("/owner")
    @ApiOperation("Получение всех материалов по оунеру")
    public ResponseEntity<?> getAllSubByOwner(@RequestParam Long mtOwner) {
        return buildResponse(this.materialService.getAllMaterialsByOwner(mtOwner), HttpStatus.OK);
    }
}
