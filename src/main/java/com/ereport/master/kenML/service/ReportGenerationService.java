package com.ereport.master.kenML.service;

import com.ereport.master.domain.MaterialList;
import com.ereport.master.kenML.domain.dto.MaterialDTO;
import com.ereport.master.kenML.domain.dto.ReportGenerationResponse;
import com.ereport.master.service.MaterialListService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportGenerationService {
    private final ObjectService objectService;
    private final MaterialListService materialListService;

    public ReportGenerationService(ObjectService objectService, MaterialListService materialListService) {
        this.objectService = objectService;
        this.materialListService = materialListService;
    }


    public ReportGenerationResponse getResponse(){
        ReportGenerationResponse reportGenerationResponse = new ReportGenerationResponse();
        List<MaterialDTO> materialDTOS = new ArrayList<>();
        List<MaterialList> materialLists = materialListService.findAll();
        for(MaterialList materialList: materialLists){
            materialDTOS.add(new MaterialDTO(materialList.getMaterialName(), 0));
        }
        reportGenerationResponse.setTopTen(materialDTOS);
        reportGenerationResponse.setOverallForYears(objectService.getOverallForYear());



        return reportGenerationResponse;
    }

}
