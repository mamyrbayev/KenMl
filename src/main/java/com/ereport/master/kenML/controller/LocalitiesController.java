package com.ereport.master.kenML.controller;

import com.ereport.master.controller.BaseController;
import com.ereport.master.kenML.service.LocalitiesService;
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
@RequestMapping("/api/localities")
public class LocalitiesController extends BaseController {
    private final LocalitiesService localitiesService;

    @GetMapping
    public ResponseEntity<?> getAllByMaterialCode(@RequestParam String codeSnb) {
        return buildResponse(this.localitiesService.getAllByMaterialCode(codeSnb), HttpStatus.OK);
    }
}
