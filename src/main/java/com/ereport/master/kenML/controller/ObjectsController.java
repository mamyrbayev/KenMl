package com.ereport.master.kenML.controller;

import com.ereport.master.controller.BaseController;
import com.ereport.master.kenML.service.ObjectService;
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
@RequestMapping("/api/objects")
@Api(tags = "Обьекты")
public class ObjectsController extends BaseController {

    private final ObjectService objectService;

    @GetMapping
    public ResponseEntity<?> getAllSubByOwner() {
        return buildResponse(this.objectService.getOverallForYear(), HttpStatus.OK);
    }
}
