package com.ereport.master.service;

import com.ereport.master.domain.Report;
import com.ereport.master.domain.enums.Status;
import com.ereport.master.domain.Publications;
import com.ereport.master.exceptions.ErrorCode;
import com.ereport.master.exceptions.ServiceException;
import com.ereport.master.repository.PublicationsRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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


    public void createPublicationByScheduler() throws ParseException {
        List<Report> reports = reportService.findAll();
        for(Report report: reports){
            List<Integer> sendingDays = getSendingDays(report);
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

            if(sendingDays.size() > 0){
                if(sendingDays.contains(dayOfWeek)){
                    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                    String fullDate = dateFormat.format(new Date());

                    DateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
                    String currentDate = dateFormat1.format(new Date());

                    String sendingDate = currentDate + " " + report.getTimeOfPublication();

                    if(fullDate.equals(sendingDate)){
                        System.out.println("!!! GENERATED PDF !!!");
                    }
                }
            }
        }
    }

    public void sendPublicationByScheduler() throws ParseException {
        List<Publications> publications = publicationsRepo.findAllByStatus(String.valueOf(Status.PUBLISHED));
        for(Publications publication: publications){
            long tenMinsInMills = publication.getReport().getSendAfterTime();//millisecs

            Calendar date = Calendar.getInstance();
            date.setTime(new Date());
            long t = date.getTimeInMillis();
            Date afterAddingTenMins = new Date(t + tenMinsInMills);

            if(publication.getCreatedAt().equals(afterAddingTenMins)){
                System.out.println("!!! SEND PDF TO CONTRACTORS !!!");
            }
        }
    }


    public List<Integer> getSendingDays(Report report){
        List<Integer> integers = new ArrayList<>();
        if(report.isGenerateInSunday()){
            integers.add(1);
        }
        if(report.isGenerateInMonday()){
            integers.add(2);
        }
        if(report.isGenerateInTuesday()){
            integers.add(3);
        }
        if(report.isGenerateInWednesday()){
            integers.add(4);
        }
        if(report.isGenerateInThursday()){
            integers.add(5);
        }
        if(report.isGenerateInFriday()){
            integers.add(6);
        }
        if(report.isGenerateInSaturday()){
            integers.add(7);
        }
        return integers;
    }

}
