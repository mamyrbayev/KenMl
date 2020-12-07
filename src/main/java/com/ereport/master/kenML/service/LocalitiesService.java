package com.ereport.master.kenML.service;

import com.ereport.master.kenML.domain.Companies;
import com.ereport.master.kenML.domain.Localities;
import com.ereport.master.kenML.domain.dto.CompaniesDto;
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
    private final ObjectService objectService;

    public LocalitiesService(LocalitiesRepo localitiesRepo, CompaniesService companiesService, ResourcesService resourcesService, ObjectService objectService) {
        this.localitiesRepo = localitiesRepo;
        this.companiesService = companiesService;
        this.resourcesService = resourcesService;
        this.objectService = objectService;
    }

    public List<LocalitiesByMatrial> getAllByMaterialCode(String mtCode){

        List<LocalitiesByMatrial> localitiesByMatrials = new ArrayList<>();

        List<Localities> localities = localitiesRepo.findAllByMaterialCode(mtCode);
        for(Localities locality: localities){
            List<Companies> companies = companiesService.findAllByLocalityId(locality.getId());
            List<CompaniesDto> companiesDtos = new ArrayList<>();

            for(Companies company: companies){
                OverallVolumeAndPrice overallForCompany = resourcesService.getOverallForCompany(mtCode, company.getId(), locality.getId());

                CompaniesDto companiesDto = CompaniesDto.builder()
                        .id(company.getId())
                        .bin(company.getBin())
                        .title(company.getTitle())
                        .directorName(company.getDirectorName())
                        .directorPhone(company.getDirectorPhone())
                        .emailAddress(company.getEmailAddress())
                        .physicalAddress(company.getPhysicalAddress())
                        .categoryID(company.getCategoryID())
                        .objectsDto(objectService.getObjectsByCompanyAndLocality(mtCode, company.getId(), locality.getId()))
                        .overallPrice(overallForCompany.getPrice())
                        .overallVolume(overallForCompany.getVolume())
                        .build();
                companiesDtos.add(companiesDto);

            }

            OverallVolumeAndPrice overallVolumeAndPrice = resourcesService.getOverallVolumeAndPrice(mtCode, locality.getId());

            LocalitiesByMatrial localitiesByMatrial = LocalitiesByMatrial.builder()
                    .localities(locality)
                    .companiesList(companiesDtos)
                    .overallPrice(overallVolumeAndPrice.getPrice())
                    .overallVolume(overallVolumeAndPrice.getVolume())
                    .build();
            localitiesByMatrials.add(localitiesByMatrial);
        }

        return localitiesByMatrials;
    }
}
