package com.ereport.master.kenML.service;

import com.ereport.master.kenML.domain.Companies;
import com.ereport.master.kenML.domain.Localities;
import com.ereport.master.kenML.domain.Resources;
import com.ereport.master.kenML.domain.dto.LocalitiesByMatrial;
import com.ereport.master.kenML.domain.dto.OverallVolumeAndPrice;
import com.ereport.master.kenML.repository.LocalitiesRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocalitiesService {
    private final LocalitiesRepo localitiesRepo;
    private final CompaniesService companiesService;
    private final ResourcesService resourcesService;

    public LocalitiesService(LocalitiesRepo localitiesRepo, CompaniesService companiesService, ResourcesService resourcesService) {
        this.localitiesRepo = localitiesRepo;
        this.companiesService = companiesService;
        this.resourcesService = resourcesService;
    }

    public List<LocalitiesByMatrial> getAllByMaterialCode(String mtCode){

        List<LocalitiesByMatrial> localitiesByMatrials = new ArrayList<>();

        List<Localities> localities = localitiesRepo.findAllByMaterialCode(mtCode);
        for(Localities locality: localities){
            List<Companies> companies = companiesService.findAllByLocalityId(locality.getId());

            OverallVolumeAndPrice overallVolumeAndPrice = resourcesService.getOverallVolumeAndPrice(mtCode, locality.getId());

            LocalitiesByMatrial localitiesByMatrial = LocalitiesByMatrial.builder()
                    .localities(locality)
                    .companiesList(companies)
                    .overallPrice(overallVolumeAndPrice.getPrice())
                    .overallVolume(overallVolumeAndPrice.getVolume())
                    .build();
            localitiesByMatrials.add(localitiesByMatrial);
        }

        return localitiesByMatrials;
    }
}
