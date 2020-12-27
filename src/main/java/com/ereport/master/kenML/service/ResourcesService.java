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
            volume += resources1.getResourceVolume();
            price = resources1.getPrice();
        }


        return OverallVolumeAndPrice.builder()
                .price(price * volume)
                .volume(volume)
                .build();
    }

    public OverallVolumeAndPrice getOverallVolumeAndPriceForMaterial(String codeSnb){
        List<Resources> resources = resourcesRepo.findAllByCodeSNB(codeSnb);
        Float volume = 0f;
        Float price = 0f;
        String measurer = null;
        for(Resources resources1: resources){
            volume += resources1.getResourceVolume();
            price = resources1.getPrice();
            measurer = resources1.getMeasurer();
        }
        return OverallVolumeAndPrice.builder()
                .price(price * volume)
                .volume(volume)
                .measurer(measurer)
                .build();
    }



    public OverallVolumeAndPrice getOverallForCompany(String codeSnb, Integer companyId, Integer localityId){
        List<Resources> resources = resourcesRepo.findallByForCompany(codeSnb, companyId, localityId);
        Float volume = 0f;
        Float price = 0f;

        for(Resources resources1: resources){
            volume += resources1.getResourceVolume();
            price = resources1.getPrice();
        }
        return OverallVolumeAndPrice.builder()
                .price(price * volume)
                .volume(volume)
                .build();
    }

    public OverallVolumeAndPrice getOverallForObject(String codeSnb, Integer objectId){
        List<Resources> resources = resourcesRepo.findAllByObjectIdd(codeSnb, objectId);
        Float volume = 0f;
        Float price = 0f;

        for(Resources resources1: resources){
            volume += resources1.getResourceVolume();
            price = resources1.getPrice();
        }
        return OverallVolumeAndPrice.builder()
                .price(price * volume)
                .volume(volume)
                .build();
    }


    public OverallVolumeAndPrice getOverallForFileSection(String codeSnb, Integer fileSectionId){
        List<Resources> resources = resourcesRepo.findAllByFileSectionIdd(codeSnb, fileSectionId);
        Float volume = 0f;
        Float price = 0f;

        for(Resources resources1: resources){
            volume += resources1.getResourceVolume();
            price = resources1.getPrice();
        }
        return OverallVolumeAndPrice.builder()
                .price(price * volume)
                .volume(volume)
                .build();
    }

 }
