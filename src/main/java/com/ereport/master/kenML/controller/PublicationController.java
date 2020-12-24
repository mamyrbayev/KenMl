package com.ereport.master.kenML.controller;

import com.ereport.master.kenML.domain.Publications;
import com.ereport.master.kenML.service.PublicationsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/api/publications")
public class PublicationController extends BaseController {
    private final PublicationsService publicationsService;

    public PublicationController(PublicationsService publicationsService) {
        this.publicationsService = publicationsService;
    }

    @Value("${system.file_locations}")
    private String location;

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

    @GetMapping("/send/email")
    public ResponseEntity<?> sendEmailToCompanies(@RequestParam Integer id) throws IOException {
        publicationsService.sendEmailByPublicationId(id);
        return buildResponse("sended", HttpStatus.OK);
    }

    @RequestMapping(path = "/download", method = RequestMethod.GET)
    public ResponseEntity<?> download(Integer id) throws IOException {
        Publications publications = publicationsService.getById(id);
        File file = new File(location + "/" + publications.getFilePath());
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName());
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
        InputStreamResource resource = new InputStreamResource(new FileInputStream(location + "/" + publications.getFilePath()));
//        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(file.toPath()));
        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

}
