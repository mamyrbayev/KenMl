package com.ereport.master.kenML.service;

import com.ereport.master.exceptions.ServiceException;
import com.ereport.master.kenML.domain.Material;
import com.ereport.master.kenML.domain.ReportMaterials;
import com.ereport.master.kenML.domain.dto.ReportMaterialsResponse;
import com.ereport.master.kenML.repository.ReportMaterialsRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportMaterialsService {

    private final ReportMaterialsRepo reportMaterialsRepo;
    private final MaterialService materialService;

    public ReportMaterialsService(ReportMaterialsRepo reportMaterialsRepo, MaterialService materialService) {
        this.reportMaterialsRepo = reportMaterialsRepo;
        this.materialService = materialService;
    }


    public ReportMaterials save(ReportMaterials r){
        return reportMaterialsRepo.add(r.getReportId(), r.getMtCode());
    }

    public List<ReportMaterialsResponse> findAll(){
        List<ReportMaterials> reportMaterials = reportMaterialsRepo.findAllReportMaterialsByDeletedAtIsNull();
        List<ReportMaterialsResponse> reportMaterialsResponses = new ArrayList<>();
        for(ReportMaterials reportMaterial: reportMaterials){
            Material material = materialService.getByMaterialCode(reportMaterial.getMtCode());
            reportMaterialsResponses.add(ReportMaterialsResponse.builder()
                    .id(reportMaterial.getId())
                    .mtLink(material.getMtLink())
                    .mtMeasure(material.getMtMeasure())
                    .mtName(material.getMtName())
                    .mtOwner(material.getMtOwner())
                    .mtCode(material.getMtCode())
                    .reportId(reportMaterial.getReportId())
                    .build());
        }
        return reportMaterialsResponses;
    }


    public List<ReportMaterialsResponse> useFilter(List<ReportMaterials> reportMaterialsList) throws ServiceException {
        try{
            reportMaterialsRepo.deleteraws();
        }catch (Exception e){

        }
        List<ReportMaterials> resp = new ArrayList<>();
        for(ReportMaterials reportMaterials: reportMaterialsList){
            resp.add(save(reportMaterials));
        }
        List<ReportMaterialsResponse> reportMaterialsResponses = new ArrayList<>();
        for(ReportMaterials reportMaterial: resp){
            Material material = materialService.getByMaterialCode(reportMaterial.getMtCode());
            reportMaterialsResponses.add(ReportMaterialsResponse.builder()
                    .id(reportMaterial.getId())
                    .mtLink(material.getMtLink())
                    .mtMeasure(material.getMtMeasure())
                    .mtName(material.getMtName())
                    .mtOwner(material.getMtOwner())
                    .mtCode(material.getMtCode())
                    .reportId(reportMaterial.getReportId())
                    .build());
        }
        return reportMaterialsResponses;
    }



}
