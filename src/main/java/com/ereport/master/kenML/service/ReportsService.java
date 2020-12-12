package com.ereport.master.kenML.service;

import com.ereport.master.kenML.domain.Reports;
import com.ereport.master.kenML.repository.ReportsRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportsService {
    private final ReportsRepo reportsRepo;

    public ReportsService(ReportsRepo reportsRepo) {
        this.reportsRepo = reportsRepo;
    }

    public Reports save(Reports r){
        return reportsRepo.add(r.getName(), r.getTimeOfPublication(), r.isAutoSending(), r.isGenerateInMonday(),
                r.isGenerateInTuesday(), r.isGenerateInWednesday(), r.isGenerateInThursday(), r.isGenerateInFriday(),
                r.isGenerateInSaturday(), r.isGenerateInSunday(), r.getIconPath(), r.getSendAfterTime(),
                r.isPublicate(), r.getCreatedBy(), r.getCreatedAt(), r.getUpdatedBy(), r.getUpdatedAt());
    }

    public List<Reports> findAll(){
        return reportsRepo.findAllReportsByDeletedAtIsNull();
    }

    public Reports findById(Integer id){
        return reportsRepo.findReportsByIdAndDeletedAtIsNull(id);
    }


    public Reports update(Reports r){
        return reportsRepo.update(r.getId(), r.getName(), r.getTimeOfPublication(), r.isAutoSending(), r.isGenerateInMonday(),
                r.isGenerateInTuesday(), r.isGenerateInWednesday(), r.isGenerateInThursday(), r.isGenerateInFriday(),
                r.isGenerateInSaturday(), r.isGenerateInSunday(), r.getIconPath(), r.getSendAfterTime(),
                r.isPublicate(), r.getUpdatedBy(), r.getUpdatedAt());
    }
}
