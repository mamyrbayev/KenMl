package com.ereport.master.service;

import com.ereport.master.domain.Report;
import com.ereport.master.repository.PublicationsRepo;
import com.ereport.master.repository.ReportRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReportService {
    private final ReportRepo reportRepo;
    private final PublicationsRepo publicationsRepo;


    public ReportService(ReportRepo reportRepo, PublicationsRepo publicationsRepo) {
        this.reportRepo = reportRepo;
        this.publicationsRepo = publicationsRepo;

    }

    public List<Report> findAll() {
        return reportRepo.findAllByDeletedAtIsNull();
    }

    public Report add(Long id, Report report) {
        return reportRepo.save(report);
    }

    public Report findId(Long id) {
        return reportRepo.findByIdAndDeletedAtIsNull(id);
    }

    public String update(Long id, String name, String daysOfPublications) {
        Report report = reportRepo.findByIdAndDeletedAtIsNull(id);
        report.setName(name);
        report.setDaysOfPublications(daysOfPublications);
        reportRepo.save(report);
        return "updated";
    }

    public void delete(Long id) {
        Report report = reportRepo.findByIdAndDeletedAtIsNull(id);
        Date date = new Date();
        report.setDeletedAt(date);
        reportRepo.save(report);
    }
}
