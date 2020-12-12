package com.ereport.master.kenML.service.wrapper;

import com.ereport.master.kenML.service.PublicationsService;
import com.ereport.master.kenML.service.ReportsService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class ServiceWrapper {
    private final ReportsService reportsService;
    private final PublicationsService publicationsService;

    @Lazy
    public ServiceWrapper(ReportsService reportsService, PublicationsService publicationsService) {
        this.reportsService = reportsService;
        this.publicationsService = publicationsService;
    }

    public ReportsService getReportsService() {
        return reportsService;
    }

    public PublicationsService getPublicationsService() {
        return publicationsService;
    }
}
