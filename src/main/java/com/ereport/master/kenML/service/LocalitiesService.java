package com.ereport.master.kenML.service;

import com.ereport.master.kenML.domain.Companies;
import com.ereport.master.kenML.domain.Localities;
import com.ereport.master.kenML.domain.dto.LocalitiesByMatrial;
import com.ereport.master.kenML.repository.LocalitiesRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocalitiesService {
    private final LocalitiesRepo localitiesRepo;
    private final CompaniesService companiesService;

    public LocalitiesService(LocalitiesRepo localitiesRepo, CompaniesService companiesService) {
        this.localitiesRepo = localitiesRepo;
        this.companiesService = companiesService;
    }

    public List<LocalitiesByMatrial> getAllByMaterialCode(String mtCode){

        List<LocalitiesByMatrial> localitiesByMatrials = new ArrayList<>();

        List<Localities> localities = localitiesRepo.findAllByMaterialCode(mtCode);
        for(Localities locality: localities){
            List<Companies> companies = companiesService.findAllByLocalityId(locality.getId());

            LocalitiesByMatrial localitiesByMatrial = LocalitiesByMatrial.builder()
                    .localities(locality)
                    .companiesList(companies)
                    .build();
            localitiesByMatrials.add(localitiesByMatrial);
        }

        return localitiesByMatrials;
    }
}
