package com.ereport.master.kenML.service;

import com.ereport.master.domain.enums.Status;
import com.ereport.master.kenML.domain.Publications;
import com.ereport.master.kenML.domain.Reports;
import com.ereport.master.kenML.repository.PublicationsRepo;
import com.ereport.master.kenML.service.wrapper.ServiceWrapper;
import com.ereport.master.service.HtmlToPdfService;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
    private final ServiceWrapper serviceWrapper;
    private final HtmlToPdfService htmlToPdfService;

    public PublicationsService(PublicationsRepo publicationsRepo, ServiceWrapper serviceWrapper, HtmlToPdfService htmlToPdfService) {
        this.publicationsRepo = publicationsRepo;
        this.serviceWrapper = serviceWrapper;
        this.htmlToPdfService = htmlToPdfService;
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


    public List<Integer> getSendingDays(Reports report){
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



    public void createPublicationByScheduler() throws ParseException, IOException, InterruptedException {
        List<Reports> reports = serviceWrapper.getReportsService().findAll();
        for(Reports report: reports){
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
                        String fileName = htmlToPdfService.generate();
                        System.out.println("PDF file name " + fileName);
                    }
                }
            }
        }
    }

    public void sendPublicationByScheduler() throws ParseException {
        List<Publications> publications = publicationsRepo.findAllPublicationsByStatus(String.valueOf(Status.PUBLISHED));
        for(Publications publication: publications){
            Reports reports = serviceWrapper.getReportsService().findById(publication.getReportId());
            long tenMinsInMills = reports.getSendAfterTime();//millisecs

            Calendar date = Calendar.getInstance();
            date.setTime(new Date());
            long t = date.getTimeInMillis();
            Date afterAddingTenMins = new Date(t + tenMinsInMills);

            if(publication.getCreatedAt().equals(afterAddingTenMins)){
                System.out.println("!!! SEND PDF TO CONTRACTORS !!!");
            }
        }
    }
}
