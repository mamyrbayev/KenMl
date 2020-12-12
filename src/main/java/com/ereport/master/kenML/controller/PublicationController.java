package com.ereport.master.kenML.controller;

import com.ereport.master.kenML.domain.Publications;
import com.ereport.master.kenML.service.PublicationsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/publications")
public class PublicationController extends BaseController {
    private final PublicationsService publicationsService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Publications publications){
        return buildResponse(publicationsService.add(publications), HttpStatus.OK);
    }


    @GetMapping("/findAll")
    public ResponseEntity<?> getAll() {
        return buildResponse(publicationsService.getAll(), HttpStatus.OK);
    }


    @GetMapping("/findId")
    public ResponseEntity<?> getById(@RequestParam Integer id) {
        return buildResponse(publicationsService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/report")
    public ResponseEntity<?> getByReportId(@RequestParam Integer id) {
        return buildResponse(publicationsService.getAllByReportId(id), HttpStatus.OK);
    }

//    @GetMapping("/status")
//    public ResponseEntity<?> getById(@RequestParam String status) {
//        return buildResponse(publicationsService.getAllByStatus(status), HttpStatus.OK);
//    }
//
//    @GetMapping("/last/report")
//    public ResponseEntity<?> getLastByReportId(@RequestParam Integer id) {
//        return buildResponse(publicationsService.getLastByReportId(id), HttpStatus.OK);
//    }
}
