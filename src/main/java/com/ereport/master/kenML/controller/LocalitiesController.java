package com.ereport.master.kenML.controller;

import com.ereport.master.kenML.service.LocalitiesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/localities")
public class LocalitiesController extends BaseController {
    private final LocalitiesService localitiesService;

    @GetMapping
    public ResponseEntity<?> getAllByMaterialCode(@RequestParam String codeSnb) throws ParseException {
        return buildResponse(this.localitiesService.getAllByMaterialCode(codeSnb), HttpStatus.OK);
    }
}
