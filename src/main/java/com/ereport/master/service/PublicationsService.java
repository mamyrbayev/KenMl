package com.ereport.master.service;

import com.ereport.master.Status;
import com.ereport.master.domain.Publications;
import com.ereport.master.domain.Report;
import com.ereport.master.repository.PublicationsRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PublicationsService {
    private final PublicationsRepo publicationsRepo;
    private final ReportService reportService;

    public PublicationsService(PublicationsRepo publicationsRepo, ReportService reportService) {
        this.publicationsRepo = publicationsRepo;
        this.reportService = reportService;
    }

    public List<Publications> findAll() {
        return publicationsRepo.findAllByDeletedAtIsNull();
    }

    public Publications add(Publications publications, Long id) {
        Report report = reportService.findId(id);
        publications.setReport(report);
        if (publications.getReport().getId() == null) {
            System.out.println("there is no such id");
            return publications;
        } else {
            publicationsRepo.save(publications);
            return publications;
        }
    }

    public Publications findId(Long id) {
        return publicationsRepo.findByIdAndDeletedAtIsNull(id);
    }

    public String update(Long id, Date publicationDate, Date sendingDate, Status status) {
        Publications publications = publicationsRepo.findByIdAndDeletedAtIsNull(id);
        publications.setPublicationDate(publicationDate);
        publications.setSendingDate(sendingDate);
        publications.setStatus(status);
        publicationsRepo.save(publications);
        return "updated";
    }

    public void delete(Long id) {
        Publications publications = publicationsRepo.findByIdAndDeletedAtIsNull(id);
        Date date = new Date();
        publications.setDeletedAt(date);
        publicationsRepo.save(publications);
    }
}
