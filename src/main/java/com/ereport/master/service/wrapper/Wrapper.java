package com.ereport.master.service.wrapper;

import com.ereport.master.service.MaterialListService;
import com.ereport.master.service.PublicationsService;
import com.ereport.master.service.ReportService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class Wrapper {
    private final ReportService reportService;
    private final PublicationsService publicationsService;
    private final MaterialListService materialListService;


    @Lazy
    public Wrapper(ReportService reportService, PublicationsService publicationsService, MaterialListService materialListService) {
        this.reportService = reportService;
        this.publicationsService = publicationsService;
        this.materialListService = materialListService;
    }

    public ReportService getReportService() {
        return reportService;
    }

    public PublicationsService getPublicationsService() {
        return publicationsService;
    }

    public MaterialListService getMaterialListService(){
        return materialListService;
    }
}
