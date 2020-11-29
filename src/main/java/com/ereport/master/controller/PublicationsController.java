package com.ereport.master.controller;

import com.ereport.master.domain.enums.Status;
import com.ereport.master.domain.Publications;
import com.ereport.master.exceptions.ServiceException;
import com.ereport.master.service.PublicationsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
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
    public ResponseEntity<?> add(@RequestBody Publications publications) throws ServiceException {
        return buildResponse(publicationsService.add(publications), HttpStatus.OK);
    }

    @GetMapping("/findId")
    public ResponseEntity<?> getId(@RequestParam Long id){
        return buildResponse(publicationsService.findId(id), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Publications publications) throws ServiceException {
        return buildResponse(publicationsService.update(publications), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam Long id){
        publicationsService.delete(id);
        return buildResponse("deleted", HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() throws ParseException {
        publicationsService.sendPublicationByScheduler();
        return buildResponse("done", HttpStatus.OK);
    }

}
