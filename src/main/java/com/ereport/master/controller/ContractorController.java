package com.ereport.master.controller;

import com.ereport.master.domain.Contractor;
import com.ereport.master.service.ContractorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contractor")
public class ContractorController extends BaseController {
    private final ContractorService contractorService;

    public ContractorController(ContractorService contractorService) {
        this.contractorService = contractorService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> getAll() {
        return buildResponse(contractorService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestParam Long id, @RequestBody Contractor contractor) {
        return buildResponse(contractorService.add(id, contractor), HttpStatus.OK);
    }

    @GetMapping("/findId")
    public ResponseEntity<?> getId(@RequestParam Long id) {
        return buildResponse(contractorService.findId(id), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestParam Long id,
                                    @RequestBody String contrName,
                                    @RequestBody String eMail,
                                    @RequestBody int phoneNum) {
        return buildResponse(contractorService.update(id, contrName, eMail, phoneNum), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam Long id) {
        contractorService.delete(id);
        return buildResponse("deleted", HttpStatus.OK);
    }
}
