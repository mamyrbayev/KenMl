package com.ereport.master.controller;

import com.ereport.master.domain.Report;
import com.ereport.master.service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
public class ReportController extends BaseController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> getAll() {
        return buildResponse(reportService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestParam Long id, @RequestBody Report report) {
        return buildResponse(reportService.add(id, report), HttpStatus.OK);
    }

    @GetMapping("/findId")
    public ResponseEntity<?> getId(@RequestParam Long id) {
        return buildResponse(reportService.findId(id), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestParam Long id,
                                    @RequestParam String name,
                                    @RequestParam String daysOfPublications) {
        return buildResponse(reportService.update(id, name, daysOfPublications), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam Long id) {
        reportService.delete(id);
        return buildResponse("deleted", HttpStatus.OK);
    }
}
