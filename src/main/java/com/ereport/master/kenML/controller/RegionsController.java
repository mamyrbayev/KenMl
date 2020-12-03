package com.ereport.master.kenML.controller;

import com.ereport.master.controller.BaseController;
import com.ereport.master.kenML.service.RegionsService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/regions")
@Api(tags = "Регионы")
public class RegionsController extends BaseController {

    private final RegionsService regionsService;

    @GetMapping
    public ResponseEntity<?> getAllSubByOwner() {
        return buildResponse(this.regionsService.getOverallForYear(), HttpStatus.OK);
    }
}
