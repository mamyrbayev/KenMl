package com.ereport.master.service.wrapper;

import com.ereport.master.service.MaterialListService;
import com.ereport.master.service.PublicationsServiceOld;
import com.ereport.master.service.ReportService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class Wrapper {
    private final ReportService reportService;
    private final PublicationsServiceOld publicationsServiceOld;
    private final MaterialListService materialListService;


    @Lazy
    public Wrapper(ReportService reportService, PublicationsServiceOld publicationsServiceOld, MaterialListService materialListService) {
        this.reportService = reportService;
        this.publicationsServiceOld = publicationsServiceOld;
        this.materialListService = materialListService;
    }

    public ReportService getReportService() {
        return reportService;
    }

    public PublicationsServiceOld getPublicationsServiceOld() {
        return publicationsServiceOld;
    }

    public MaterialListService getMaterialListService(){
        return materialListService;
    }
}
