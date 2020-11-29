package com.ereport.master.service;

import com.ereport.master.domain.Category;
import com.ereport.master.domain.Report;
import com.ereport.master.exceptions.ErrorCode;
import com.ereport.master.exceptions.ServiceException;
import com.ereport.master.repository.PublicationsRepo;
import com.ereport.master.repository.ReportRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReportService {
    private final ReportRepo reportRepo;

    public ReportService(ReportRepo reportRepo) {
        this.reportRepo = reportRepo;
    }

    public List<Report> findAll() {
        return reportRepo.findAllByDeletedAtIsNull();
    }

    public Report add(Report report) throws ServiceException {
        if(report.getId() == null){
            return reportRepo.save(report);
        }else{
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("id is null")
                    .build();
        }
    }

    public Report findById(Long id) {
        return reportRepo.findByIdAndDeletedAtIsNull(id);
    }

    public Report update(Report report) throws ServiceException {
        if(report.getId() != null){
            return reportRepo.save(report);
        }else{
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("id is null")
                    .build();
        }
    }

    public void delete(Long id) {
        Report report = reportRepo.findByIdAndDeletedAtIsNull(id);
        Date date = new Date();
        report.setDeletedAt(date);
        reportRepo.save(report);
    }

    public List<Category> setCategoryList(Long id, List<Category> categories) throws ServiceException {
        Report report = findById(id);
        report.setCategory(categories);
        update(report);
        return categories;
    }
}
