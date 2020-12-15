package com.ereport.master.kenML.service;

import com.ereport.master.email.emailHelper.EmailHelper;
import com.ereport.master.kenML.domain.Companies;
import com.ereport.master.kenML.domain.Publications;
import com.ereport.master.kenML.domain.Reports;
import com.ereport.master.kenML.domain.dto.PublicationsResponse;
import com.ereport.master.kenML.domain.enums.Status;
import com.ereport.master.kenML.repository.CompaniesRepo;
import com.ereport.master.kenML.repository.PublicationsRepo;
import com.ereport.master.kenML.service.wrapper.ServiceWrapper;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.File;
import java.io.FileInputStream;
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
    private final CompaniesRepo companiesRepo;
    private final EmailHelper emailHelper;

    @Value("${system.file_locations}")
    private String location;

    public PublicationsService(PublicationsRepo publicationsRepo, ServiceWrapper serviceWrapper, HtmlToPdfService htmlToPdfService, CompaniesRepo companiesRepo, EmailHelper emailHelper) {
        this.publicationsRepo = publicationsRepo;
        this.serviceWrapper = serviceWrapper;
        this.htmlToPdfService = htmlToPdfService;
        this.companiesRepo = companiesRepo;
        this.emailHelper = emailHelper;
    }

    public Publications add(Publications p){
        return publicationsRepo.add(p.getReportId(), p.getPublicationDate(), p.getSendingDate(), p.getStatus().toString(),
                p.isAutoSending(), p.getFilePath(), p.getCreatedBy(), p.getCreatedAt(), p.getUpdatedBy(),
                p.getUpdatedAt());
    }

    public Publications update(Publications p){
        return publicationsRepo.update(p.getId(), p.getReportId(), p.getPublicationDate(), p.getSendingDate(), p.getStatus().toString(),
                p.isAutoSending(), p.getFilePath(), p.getCreatedBy(), p.getCreatedAt(), p.getUpdatedBy(),
                p.getUpdatedAt());
    }

    public List<Publications> getAll(){
        return publicationsRepo.findAllPublicationsByDeletedAtIsNull();
    }

    public Publications getById(Integer id){
        return publicationsRepo.findPublicationsByIdAndDeletedAtIsNull(id);
    }

    public List<PublicationsResponse> getAllByReportId(Integer id){
        List<PublicationsResponse> publicationsResponses = new ArrayList<>();
        List<Publications> publications = publicationsRepo.findPublicationsByDeletedAtIsNullAndReportId(id);
        List<Companies> companies = companiesRepo.findAllCompaniesByReportId(id);
        for(Publications publication: publications){
            publicationsResponses.add(PublicationsResponse.builder()
                    .id(publication.getId())
                    .autoSending(publication.isAutoSending())
                    .createdAt(publication.getCreatedAt())
                    .createdBy(publication.getCreatedBy())
                    .filePath(publication.getFilePath())
                    .publicationDate(publication.getPublicationDate())
                    .receivers(companies)
                    .reportId(publication.getReportId())
                    .sendingDate(publication.getSendingDate())
                    .status(publication.getStatus())
                    .updatedAt(publication.getUpdatedAt())
                    .updatedBy(publication.getUpdatedBy())
                    .build());
        }
        return publicationsResponses;
    }

    public List<Publications> getAllByStatus(String status){
        return publicationsRepo.findAllPublicationsByStatus(status);
    }

    public Publications getLastByReportId(Integer id){
        return publicationsRepo.findLastPublicationsByDeletedAtIsNullAndReportId(id);
    }

    public String getStatusFromPublications(Integer reportId){
        List<PublicationsResponse> publications = getAllByReportId(reportId);
        int sent = 0;
        for(PublicationsResponse publications1: publications){
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



    public void createPublicationByScheduler() throws IOException, InterruptedException {
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

                        Publications publications = Publications.builder()
                                .autoSending(report.isAutoSending())
                                .createdAt(new Date())
                                .filePath(fileName)
                                .publicationDate(new Date())
                                .reportId(report.getId())
                                .status(Status.PUBLISHED)
                                .updatedAt(new Date())
                                .build();
                        add(publications);
                    }
                }
            }
        }
    }

    public void sendPublicationByScheduler() throws ParseException, IOException {
        List<Publications> publications = publicationsRepo.findAllPublicationsByStatus(String.valueOf(Status.PUBLISHED));
        for(Publications publication: publications){
            Reports reports = serviceWrapper.getReportsService().findById(publication.getReportId());
            long tenMinsInMills = reports.getSendAfterTime();//millisecs

            Calendar date = Calendar.getInstance();
            date.setTime(new Date());
            long t = date.getTimeInMillis();
            Date afterAddingTenMins = new Date(t + tenMinsInMills);

            if(publication.getCreatedAt().equals(afterAddingTenMins)){
                sendEmailByPublicationId(publication.getId());
            }
        }
    }

    public void sendEmailByPublicationId(Integer id) throws IOException {
        Publications publications = getById(id);
        if(publications != null){
            if(publications.getFilePath() != null){
                if(publications.getReportId() != null){
                    try {
                        File file = new File(location + publications.getFilePath());
                        FileInputStream input = new FileInputStream(file);
                        MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "text/plain",
                                IOUtils.toByteArray(input));

                        List<Companies> companies = companiesRepo.findAllCompaniesByReportId(publications.getReportId());

                        for(Companies company: companies){
                            if(company.getEmailAddress() != null){
                                if(isValidEmailAddress(company.getEmailAddress())){
                                    emailHelper.sendEmail(company.getEmailAddress(), multipartFile);
                                    publications.setStatus(Status.SENT);
                                    publications.setSendingDate(new Date());
                                    update(publications);
                                    System.out.println("Send to "+ company.getEmailAddress());
                                }
                            }
                        }

                    } catch (IOException e) {
                    }
                }
            }
        }
    }

    public boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
}
