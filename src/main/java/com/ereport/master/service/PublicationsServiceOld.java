package com.ereport.master.service;

import com.ereport.master.domain.Category;
import com.ereport.master.domain.Contractor;
import com.ereport.master.domain.Report;
import com.ereport.master.domain.dto.PublicationsResponse;
import com.ereport.master.domain.enums.Status;
import com.ereport.master.domain.Publications;
import com.ereport.master.exceptions.ErrorCode;
import com.ereport.master.exceptions.ServiceException;
import com.ereport.master.repository.PublicationsRepoOld;
import com.ereport.master.service.wrapper.Wrapper;
import org.springframework.http.HttpStatus;
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
public class PublicationsServiceOld {
    private final PublicationsRepoOld publicationsRepoOld;
    private final Wrapper wrapper;
    private final ContractorService contractorService;
    private final HtmlToPdfService htmlToPdfService;

    public PublicationsServiceOld(PublicationsRepoOld publicationsRepoOld, Wrapper wrapper,
                                  ContractorService contractorService, HtmlToPdfService htmlToPdfService) {
        this.publicationsRepoOld = publicationsRepoOld;
        this.wrapper = wrapper;
        this.contractorService = contractorService;
        this.htmlToPdfService = htmlToPdfService;
    }

    public List<Publications> findAll() {
        return publicationsRepoOld.findAllByDeletedAtIsNull();
    }



    public Publications add(Publications publications) throws ServiceException {
        if(publications.getId() == null){
            publicationsRepoOld.save(publications);
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
        return publicationsRepoOld.findByIdAndDeletedAtIsNull(id);
    }

    public Publications update(Publications publications) throws ServiceException {
        if(publications != null){
            publicationsRepoOld.save(publications);
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
        Publications publications = publicationsRepoOld.findByIdAndDeletedAtIsNull(id);
        Date date = new Date();
        publications.setDeletedAt(date);
        publicationsRepoOld.save(publications);
    }


    public void createPublicationByScheduler() throws ParseException, IOException, InterruptedException {
        List<Report> reports = wrapper.getReportService().findAll();
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
                        String fileName = htmlToPdfService.generate();
                        System.out.println("PDF file name " + fileName);
                    }
                }
            }
        }
    }

    public void sendPublicationByScheduler() throws ParseException {
        List<Publications> publications = publicationsRepoOld.findAllByStatus(String.valueOf(Status.PUBLISHED));
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

    public List<Publications> getAllByReportId(Long reportId) {
        return publicationsRepoOld.findAllByDeletedAtIsNullAndReportId(reportId);
    }

    public Publications getLastByReportId(Long reportId) {
        return publicationsRepoOld.findLastByDeletedAtIsNullAndReportId(reportId);
    }

    public String getStatusFromPublications(Long reportId){
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

    public PublicationsResponse findIdResp(Long id) {
        Publications publications = publicationsRepoOld.findByIdAndDeletedAtIsNull(id);
        PublicationsResponse publicationsResponse = PublicationsResponse.builder()
                .createdAt(publications.getCreatedAt())
                .autoSending(publications.isAutoSending())
                .publicationDate(publications.getPublicationDate())
                .report(wrapper.getReportService().findByIdToFront(publications.getReport().getId()))
                .status(publications.getStatus())
                .build();
        List<Contractor> contractors = new ArrayList<>();
        List<Category> categories = publications.getReport().getCategory();
        for(Category category: categories){
            contractors.addAll(contractorService.getAllByCategory(category.getId()));
        }
        publicationsResponse.setReceivers(contractors);

        return publicationsResponse;
    }

    public List<PublicationsResponse> getAllByReportIdToFront(Long reportId) {
        List<PublicationsResponse> publicationsResponses = new ArrayList<>();
        List<Publications> publications = publicationsRepoOld.findAllByDeletedAtIsNullAndReportId(reportId);

        for(Publications publications1: publications){
            publicationsResponses.add(findIdResp(publications1.getId()));
        }
        return publicationsResponses;
    }


}
