package com.ereport.master.controller;

import com.ereport.master.domain.Publications;
import com.ereport.master.exceptions.ServiceException;
import com.ereport.master.service.PublicationsServiceOld;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;


@RestController
@RequestMapping("/api/publications/old")
public class PublicationsController extends BaseController {
    private final PublicationsServiceOld publicationsServiceOld;

    public PublicationsController(PublicationsServiceOld publicationsServiceOld) {
        this.publicationsServiceOld = publicationsServiceOld;
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> getAll() {
        return buildResponse(publicationsServiceOld.findAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Publications publications) throws ServiceException {
        return buildResponse(publicationsServiceOld.add(publications), HttpStatus.OK);
    }

    @GetMapping("/findId")
    public ResponseEntity<?> getId(@RequestParam Long id){
        return buildResponse(publicationsServiceOld.findId(id), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Publications publications) throws ServiceException {
        return buildResponse(publicationsServiceOld.update(publications), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam Long id){
        publicationsServiceOld.delete(id);
        return buildResponse("deleted", HttpStatus.OK);
    }

//    @GetMapping("/test")
//    public ResponseEntity<?> test() throws ParseException {
//        publicationsServiceOld.sendPublicationByScheduler();
//        return buildResponse("done", HttpStatus.OK);
//    }


    @GetMapping("/report")
    public ResponseEntity<?> getByReportId(@RequestParam Long id){
        return buildResponse(publicationsServiceOld.getAllByReportIdToFront(id), HttpStatus.OK);
    }

}
