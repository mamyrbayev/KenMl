package com.ereport.master.service;

import com.ereport.master.domain.enums.Status;
import com.ereport.master.domain.Publications;
import com.ereport.master.exceptions.ErrorCode;
import com.ereport.master.exceptions.ServiceException;
import com.ereport.master.repository.PublicationsRepo;
import org.springframework.http.HttpStatus;
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

    public Publications add(Publications publications) throws ServiceException {
        if(publications.getId() == null){
            publicationsRepo.save(publications);
            return publications;
        }else {
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("id is not null")
                    .build();
        }
    }

    public Publications findId(Long id) {
        return publicationsRepo.findByIdAndDeletedAtIsNull(id);
    }

    public Publications update(Publications publications) throws ServiceException {
        if(publications != null){
            publicationsRepo.save(publications);
            return publications;
        }else {
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("id is null")
                    .build();
        }
    }

    public void delete(Long id) {
        Publications publications = publicationsRepo.findByIdAndDeletedAtIsNull(id);
        Date date = new Date();
        publications.setDeletedAt(date);
        publicationsRepo.save(publications);
    }
}
