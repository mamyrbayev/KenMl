package com.ereport.master.kenML.service;

import com.ereport.master.kenML.domain.Companies;
import com.ereport.master.kenML.domain.Localities;
import com.ereport.master.kenML.domain.dto.CompaniesDto;
import com.ereport.master.kenML.domain.dto.LocalitiesByMatrial;
import com.ereport.master.kenML.domain.dto.ObjectsDto;
import com.ereport.master.kenML.domain.dto.OverallVolumeAndPrice;
import com.ereport.master.kenML.repository.LocalitiesRepo;
import com.google.common.base.CharMatcher;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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


    public List<LocalitiesByMatrial> getAllByMaterialCode(String mtCode) throws ParseException {

        List<LocalitiesByMatrial> localitiesByMatrials = new ArrayList<>();

        List<Localities> localities = localitiesRepo.findAllByMaterialCode(mtCode);
        for(Localities locality: localities){
            List<Companies> companies = companiesService.findAllByLocalityId(locality.getId());
            List<CompaniesDto> companiesDtos = new ArrayList<>();

            for(Companies company: companies){
                OverallVolumeAndPrice overallForCompany = resourcesService.getOverallForCompany(mtCode, company.getId(), locality.getId());
                if(overallForCompany.getPrice() >= 1){
                    overallForCompany.setPrice((float) Math.round(overallForCompany.getPrice()));
                }else {
                    overallForCompany.setPrice(resourcesService.formatNumber(overallForCompany.getPrice()));
                }
                if(overallForCompany.getVolume() >= 1){
                    overallForCompany.setVolume((float) Math.round(overallForCompany.getVolume()));
                }else {
                    overallForCompany.setVolume(resourcesService.formatNumber(overallForCompany.getVolume()));
                }
                List<ObjectsDto> objectsDto = objectService.getObjectsByCompanyAndLocality(mtCode, company.getId(), locality.getId());
                if(objectsDto.size() > 0){
                    if(overallForCompany.getPrice() > 0f){
                        CompaniesDto companiesDto = CompaniesDto.builder()
                                .id(company.getId())
                                .bin(company.getBin())
                                .title(company.getTitle())
                                .directorName(company.getDirectorName())
                                .directorPhone(company.getDirectorPhone())
                                .emailAddress(company.getEmailAddress())
                                .physicalAddress(company.getPhysicalAddress())
                                .categoryID(company.getCategoryID())
                                .objectsDto(objectsDto)
                                .overallPrice(overallForCompany.getPrice())
                                .overallVolume(overallForCompany.getVolume())
                                .build();
                        companiesDtos.add(companiesDto);
                    }
                }

            }

            OverallVolumeAndPrice overallVolumeAndPrice = resourcesService.getOverallVolumeAndPrice(mtCode, locality.getId());
            if(overallVolumeAndPrice.getPrice() >= 1){
                overallVolumeAndPrice.setPrice((float) Math.round(overallVolumeAndPrice.getPrice()));
            }else {
                overallVolumeAndPrice.setPrice(resourcesService.formatNumber(overallVolumeAndPrice.getPrice()));
            }
            if(overallVolumeAndPrice.getVolume() >= 1){
                overallVolumeAndPrice.setVolume((float) Math.round(overallVolumeAndPrice.getVolume()));
            }else {
                overallVolumeAndPrice.setVolume(resourcesService.formatNumber(overallVolumeAndPrice.getVolume()));
            }
            if(overallVolumeAndPrice.getPrice() > 0f){
                LocalitiesByMatrial localitiesByMatrial = LocalitiesByMatrial.builder()
                        .localities(locality)
                        .companiesList(companiesDtos)
                        .overallPrice(overallVolumeAndPrice.getPrice())
                        .overallVolume(overallVolumeAndPrice.getVolume())
                        .build();

                localitiesByMatrials.add(localitiesByMatrial);
            }

        }

        return localitiesByMatrials;
    }
}
