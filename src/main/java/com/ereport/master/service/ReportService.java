package com.ereport.master.service;

import com.ereport.master.domain.Category;
import com.ereport.master.domain.Publications;
import com.ereport.master.domain.Report;
import com.ereport.master.domain.dto.ExpirationTimeResponse;
import com.ereport.master.domain.dto.ReportDTO;
import com.ereport.master.exceptions.ErrorCode;
import com.ereport.master.exceptions.ServiceException;
import com.ereport.master.repository.ReportRepo;
import com.ereport.master.service.wrapper.Wrapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReportService {
    private final ReportRepo reportRepo;
    private final Wrapper wrapper;

    public ReportService(ReportRepo reportRepo, Wrapper wrapper) {
        this.reportRepo = reportRepo;
        this.wrapper = wrapper;
    }

    public List<Report> findAll() {
        return reportRepo.findAllReportsByDeletedAtIsNull();
    }

    public List<ReportDTO> findAllToFront() {
        List<ReportDTO> reportDTOS = new ArrayList<>();
        List<Report> reports = reportRepo.findAllReportsByDeletedAtIsNull();
        for(Report report: reports){
            ReportDTO reportDTO = ReportDTO.builder()
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
                    .category(report.getCategory())
                    .numOfReports(wrapper.getPublicationsService().getAllByReportId(report.getId()).size())
                    .status(wrapper.getPublicationsService().getStatusFromPublications(report.getId()))
                    .build();
            reportDTOS.add(reportDTO);
        }
        return reportDTOS;
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

    public ReportDTO findByIdToFront (Long id) {
        Report report = reportRepo.findByIdAndDeletedAtIsNull(id);
        ReportDTO reportDTO = ReportDTO.builder()
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
                .category(report.getCategory())
                .numOfReports(wrapper.getPublicationsService().getAllByReportId(report.getId()).size())
                .status(wrapper.getPublicationsService().getStatusFromPublications(report.getId()))
                .build();
        return reportDTO;
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


    public ExpirationTimeResponse getExpirationTime(Long reportId){
        Publications publications = wrapper.getPublicationsService().getLastByReportId(reportId);


        Long sendingTime = publications.getPublicationDate().getTime() + publications.getReport().getSendAfterTime();
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
}
