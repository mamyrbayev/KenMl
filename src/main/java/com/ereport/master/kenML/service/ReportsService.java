package com.ereport.master.kenML.service;

import com.ereport.master.domain.dto.ExpirationTimeResponse;
import com.ereport.master.domain.dto.ReportDTO;
import com.ereport.master.kenML.domain.Publications;
import com.ereport.master.kenML.domain.Reports;
import com.ereport.master.kenML.domain.dto.ReportResponse;
import com.ereport.master.kenML.repository.ReportsRepo;
import com.ereport.master.kenML.service.wrapper.ServiceWrapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReportsService {
    private final ReportsRepo reportsRepo;
    private final ServiceWrapper serviceWrapper;

    public ReportsService(ReportsRepo reportsRepo, ServiceWrapper serviceWrapper) {
        this.reportsRepo = reportsRepo;
        this.serviceWrapper = serviceWrapper;
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

    public ExpirationTimeResponse getExpirationTime(Integer reportId){
        Publications publications = serviceWrapper.getPublicationsService().getLastByReportId(reportId);

        Reports reports = findById(reportId);

        Long sendingTime = publications.getPublicationDate().getTime() + reports.getSendAfterTime();
        Long leftTime = sendingTime -  new Date().getTime();

        int days = (int) ((leftTime / (1000*60*60*24)) % 7);

        leftTime = leftTime - (days * 86400000L);

        int hours   = (int) ((leftTime / (1000*60*60)) % 24);

        leftTime = leftTime - (hours * 3600000L);

        int minutes = (int) ((leftTime / (1000*60)) % 60);

        leftTime = leftTime - (minutes * 60000L);

        int seconds = (int) (leftTime / 1000) % 60 ;

        return ExpirationTimeResponse.builder()
                .day(days)
                .hour(hours)
                .minute(minutes)
                .second(seconds)
                .build();
    }



    public ReportResponse findByIdToFront(Integer id) {
        Reports report = reportsRepo.findReportsByIdAndDeletedAtIsNull(id);
        ReportResponse reportResponse = ReportResponse.builder()
                .id(report.getId())
                .name(report.getName())
                .timeOfPublication(report.getTimeOfPublication())
                .sendAfterTime(report.getSendAfterTime())
                .generateInMonday(report.isGenerateInMonday())
                .generateInTuesday(report.isGenerateInTuesday())
                .generateInWednesday(report.isGenerateInWednesday())
                .generateInThursday(report.isGenerateInThursday())
                .generateInFriday(report.isGenerateInFriday())
                .generateInSaturday(report.isGenerateInSaturday())
                .generateInSunday(report.isGenerateInSunday())
                .autoSending(report.isAutoSending())
//                .category(report.getCategory())
                .numOfReports(serviceWrapper.getPublicationsService().getAllByReportId(report.getId()).size())
                .status(serviceWrapper.getPublicationsService().getStatusFromPublications(report.getId()))
                .build();
        return reportResponse;
    }

    public List<ReportResponse> findAllToFront() {
        List<ReportResponse> reportResponses = new ArrayList<>();
        List<Reports> reports = findAll();
        for(Reports report: reports){
            reportResponses.add(findByIdToFront(report.getId()));
        }
        return reportResponses;
    }


}
