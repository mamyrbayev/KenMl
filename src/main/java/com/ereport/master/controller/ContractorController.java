package com.ereport.master.controller;

import com.ereport.master.domain.Contractor;
import com.ereport.master.exceptions.ServiceException;
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
    public ResponseEntity<?> add(@RequestBody Contractor contractor) throws ServiceException {
        return buildResponse(contractorService.add(contractor), HttpStatus.OK);
    }

    @GetMapping("/findId")
    public ResponseEntity<?> getId(@RequestParam Long id) {
        return buildResponse(contractorService.findId(id), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Contractor contractor) throws ServiceException {
        return buildResponse(contractorService.update(contractor), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam Long id) {
        contractorService.delete(id);
        return buildResponse("deleted", HttpStatus.OK);
    }

    @GetMapping("/report")
    public ResponseEntity<?> getByReportId(@RequestParam Long id) {
        return buildResponse(contractorService.getAllByReportId(id), HttpStatus.OK);
    }

    @GetMapping("/category")
    public ResponseEntity<?> getByCategoryId(@RequestParam Long id) {
        return buildResponse(contractorService.getAllByCategoryId(id), HttpStatus.OK);
    }
}
