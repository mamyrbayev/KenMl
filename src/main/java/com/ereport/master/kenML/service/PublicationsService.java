package com.ereport.master.kenML.service;

import com.ereport.master.domain.enums.Status;
import com.ereport.master.kenML.domain.Publications;
import com.ereport.master.kenML.repository.PublicationsRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicationsService {
    private final PublicationsRepo publicationsRepo;

    public PublicationsService(PublicationsRepo publicationsRepo) {
        this.publicationsRepo = publicationsRepo;
    }

    public Publications add(Publications p){
        return publicationsRepo.add(p.getReportId(), p.getPublicationDate(), p.getSendingDate(), p.getStatus().toString(),
                p.isAutoSending(), p.getFilePath(), p.getCreatedBy(), p.getCreatedAt(), p.getUpdatedBy(),
                p.getUpdatedAt());
    }

    public List<Publications> getAll(){
        return publicationsRepo.findAllPublicationsByDeletedAtIsNull();
    }

    public Publications getById(Integer id){
        return publicationsRepo.findPublicationsByIdAndDeletedAtIsNull(id);
    }

    public List<Publications> getAllByReportId(Integer id){
        return publicationsRepo.findPublicationsByDeletedAtIsNullAndReportId(id);
    }

    public List<Publications> getAllByStatus(String status){
        return publicationsRepo.findAllPublicationsByStatus(status);
    }

    public Publications getLastByReportId(Integer id){
        return publicationsRepo.findLastPublicationsByDeletedAtIsNullAndReportId(id);
    }

    public String getStatusFromPublications(Integer reportId){
        List<Publications> publications = getAllByReportId(reportId);
        int sent = 0;
        for(Publications publications1: publications){
            if(publications1.getStatus().equals(Status.SENT)) {
                sent++;
            }
        }
        String response =  "Отправлен "+sent + "/" + publications.size();
        return response;
    }


}
