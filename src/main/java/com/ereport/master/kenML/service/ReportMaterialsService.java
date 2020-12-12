package com.ereport.master.kenML.service;

import com.ereport.master.exceptions.ServiceException;
import com.ereport.master.kenML.domain.ReportMaterials;
import com.ereport.master.kenML.repository.ReportMaterialsRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportMaterialsService {

    private final ReportMaterialsRepo reportMaterialsRepo;

    public ReportMaterialsService(ReportMaterialsRepo reportMaterialsRepo) {
        this.reportMaterialsRepo = reportMaterialsRepo;
    }


    public ReportMaterials save(ReportMaterials r){
        return reportMaterialsRepo.add(r.getReportId(), r.getMaterialCode());
    }

    public List<ReportMaterials> findAll(){
        return reportMaterialsRepo.findAllReportMaterialsByDeletedAtIsNull();
    }

    public List<ReportMaterials> useFilter(List<ReportMaterials> reportMaterialsList) throws ServiceException {
        reportMaterialsRepo.deleteraws();
        List<ReportMaterials> resp = new ArrayList<>();
        for(ReportMaterials reportMaterials: reportMaterialsList){
            resp.add(save(reportMaterials));
        }
        return resp;
    }



}
