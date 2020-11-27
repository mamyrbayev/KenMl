package com.ereport.master.controller;

import com.ereport.master.Status;
import com.ereport.master.domain.Publications;
import com.ereport.master.service.PublicationsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping("/api/publications")
public class PublicationsController extends BaseController {
    private final PublicationsService publicationsService;

    public PublicationsController(PublicationsService publicationsService) {
        this.publicationsService = publicationsService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> getAll() {
        return buildResponse(publicationsService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestParam Long id, @RequestBody Publications publications) {
        return buildResponse(publicationsService.add(publications, id), HttpStatus.OK);
    }

    @GetMapping("/findId")
    public ResponseEntity<?> getId(@RequestParam Long id){
        return buildResponse(publicationsService.findId(id), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestParam Long id,
                                    @RequestParam Date publicationDate,
                                    @RequestParam Date sendingDate,
                                    @RequestParam Status status){
        return buildResponse(publicationsService.update(id, publicationDate, sendingDate, status), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam Long id){
        publicationsService.delete(id);
        return buildResponse("deleted", HttpStatus.OK);
    }
}
