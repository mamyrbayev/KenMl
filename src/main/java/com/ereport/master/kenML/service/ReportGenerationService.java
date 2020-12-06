package com.ereport.master.kenML.service;

import com.ereport.master.domain.MaterialList;
import com.ereport.master.kenML.domain.Material;
import com.ereport.master.kenML.domain.dto.LocalitiesByMatrial;
import com.ereport.master.kenML.domain.dto.MaterialDTO;
import com.ereport.master.kenML.domain.dto.Potrebnosti;
import com.ereport.master.kenML.domain.dto.ReportGenerationResponse;
import com.ereport.master.service.MaterialListService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportGenerationService {
    private final ObjectService objectService;
    private final MaterialListService materialListService;
    private final RegionsService regionsService;
    private final MaterialService materialService;
    private final LocalitiesService localitiesService;

    public ReportGenerationService(ObjectService objectService, MaterialListService materialListService, RegionsService regionsService, MaterialService materialService, LocalitiesService localitiesService) {
        this.objectService = objectService;
        this.materialListService = materialListService;
        this.regionsService = regionsService;
        this.materialService = materialService;
        this.localitiesService = localitiesService;
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
        reportGenerationResponse.setOverallForYearByRegions(regionsService.getOverallForYear());


        List<Potrebnosti> potrebnostis = new ArrayList<>();
        Material material = materialService.getByMaterialCode("210102010604");
        List<LocalitiesByMatrial> localitiesByMatrials = localitiesService.getAllByMaterialCode(material.getMyCode());
        potrebnostis.add(Potrebnosti.builder()
                .material(material)
                .localitiesByMatrials(localitiesByMatrials)
                .build());
        reportGenerationResponse.setPotrebnostis(potrebnostis);



        return reportGenerationResponse;
    }


    public List<MaterialDTO> getTopTen(){
        List<MaterialDTO> materialDTOS = new ArrayList<>();
        List<MaterialList> materialLists = materialListService.findAll();
            for(MaterialList materialList: materialLists){
            materialDTOS.add(new MaterialDTO(materialList.getMaterialName(), 0));
        }
        return materialDTOS;
    }

}
