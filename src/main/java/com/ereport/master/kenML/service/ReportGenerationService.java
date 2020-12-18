package com.ereport.master.kenML.service;

import com.ereport.master.kenML.domain.Material;
import com.ereport.master.kenML.domain.ReportMaterials;
import com.ereport.master.kenML.domain.dto.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ReportGenerationService {
    private final ObjectService objectService;
    private final ReportMaterialsService reportMaterialsService;
    private final RegionsService regionsService;
    private final MaterialService materialService;
    private final LocalitiesService localitiesService;

    public ReportGenerationService(ObjectService objectService, ReportMaterialsService reportMaterialsService, RegionsService regionsService, MaterialService materialService, LocalitiesService localitiesService) {
        this.objectService = objectService;
        this.reportMaterialsService = reportMaterialsService;
        this.regionsService = regionsService;
        this.materialService = materialService;
        this.localitiesService = localitiesService;
    }

    public ReportGenerationResponse getResponse(){
        ReportGenerationResponse reportGenerationResponse = new ReportGenerationResponse();
        List<MaterialDTO> materialDTOS = new ArrayList<>();
        List<ReportMaterialsResponse> materialLists = reportMaterialsService.findAll();
        for(ReportMaterialsResponse materialList: materialLists){
            materialDTOS.add(new MaterialDTO(materialList.getMtName(), 0));
        }
        reportGenerationResponse.setTopTen(materialDTOS);
        reportGenerationResponse.setOverallForYears(objectService.getOverallForYear());
        reportGenerationResponse.setOverallForYearByRegions(regionsService.getOverallForYear());


        List<Potrebnosti> potrebnostis = new ArrayList<>();
        List<ReportMaterialsResponse> reportMaterials = reportMaterialsService.findAll();
        List<Integer> materialIds = new ArrayList<>();
        for(ReportMaterialsResponse rmr: reportMaterials){
            materialIds.add(rmr.getId());
        }
        Integer id = Collections.min(materialIds);
        ReportMaterialsResponse reportMaterialsResponse = null;
        for(ReportMaterialsResponse rmr: reportMaterials){
            if (id.equals(rmr.getId())){
                reportMaterialsResponse = rmr;
            }
        }

        if(reportMaterialsResponse != null){
            Material material = materialService.getByMaterialCode(reportMaterialsResponse.getMtCode());
            List<LocalitiesByMatrial> localitiesByMatrials = localitiesService.getAllByMaterialCode(material.getMtCode());
            potrebnostis.add(Potrebnosti.builder()
                    .material(material)
                    .localitiesByMatrials(localitiesByMatrials)
                    .build());
            reportGenerationResponse.setPotrebnostis(potrebnostis);
        }
        return reportGenerationResponse;
    }


    public List<MaterialDTO> getTopTen(){
        List<MaterialDTO> materialDTOS = new ArrayList<>();
        List<ReportMaterialsResponse> materialLists = reportMaterialsService.findAll();
            for(ReportMaterialsResponse materialList: materialLists){
            materialDTOS.add(new MaterialDTO(materialList.getMtName(), 0));
        }
        return materialDTOS;
    }

}
