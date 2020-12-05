package com.ereport.master.kenML.service;

import com.ereport.master.kenML.domain.Resources;
import com.ereport.master.kenML.domain.dto.OverallVolumeAndPrice;
import com.ereport.master.kenML.repository.ResourcesRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourcesService {
    private final ResourcesRepo resourcesRepo;

    public ResourcesService(ResourcesRepo resourcesRepo) {
        this.resourcesRepo = resourcesRepo;
    }


    public List<Resources> findAllByLocalityId(Integer id){
        return resourcesRepo.findallByLocalityId(id);
    }

    public OverallVolumeAndPrice getOverallVolumeAndPrice(String codeSnb, Integer localityId){
        List<Resources> resources = resourcesRepo.findallByCodeSNBAndLocalityId(codeSnb, localityId);
        Float volume = 0f;
        Float price = 0f;

        for(Resources resources1: resources){
            volume += resources1.getNorma();
            price += resources1.getPrice();
        }
        return OverallVolumeAndPrice.builder()
                .price(price)
                .volume(volume)
                .build();
    }
 }
