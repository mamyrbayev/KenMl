package com.ereport.master.kenML.service;

import com.ereport.master.kenML.domain.Objects;
import com.ereport.master.kenML.domain.Resources;
import com.ereport.master.kenML.domain.dto.*;
import com.ereport.master.kenML.repository.ObjectsRepo;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ObjectService {
    private final ObjectsRepo objectsRepo;
    private final ResourcesService resourcesService;

    public ObjectService(ObjectsRepo objectsRepo, ResourcesService resourcesService) {
        this.objectsRepo = objectsRepo;
        this.resourcesService = resourcesService;
    }

    public List<Objects> getAll(){
        return objectsRepo.findAll();
    }

    public List<OverallForYear> getOverallForYear(){
        List<OverallForYear> overallForYears = new ArrayList<>();
        List<Objects> objectsList = getAll();
        List<ObjectWorkDate> workDates = new ArrayList<>();

        List<String> years = new ArrayList<>();

        for(Objects objects: objectsList){
            ObjectWorkDate objectWorkDate = ObjectWorkDate.builder()
                    .id(objects.getId())
                    .companyId(objects.getCompanyId())
                    .lastUpdatedOn(objects.getLastUpdatedOn())
                    .localityId(objects.getLocalityId())
                    .objectName(objects.getObjectName())
                    .endDate(objectsRepo.getEndDateForObject(objects.getId()))
                    .build();

            workDates.add(objectWorkDate);
            DateFormat dateFormat = new SimpleDateFormat("yyyy");
            years.add(dateFormat.format(objectWorkDate.getEndDate()));
        }

        List<String> uniqueYears = new ArrayList<>();

        uniqueYears = years.stream().distinct().collect(Collectors.toList());

        for(String uniqueYear: uniqueYears){
            int completed = 0;
            int underConstruction = 0;

            for(ObjectWorkDate objectWorkDate: workDates){
                DateFormat dateFormat = new SimpleDateFormat("yyyy");
                String year = dateFormat.format(objectWorkDate.getEndDate());

                if(uniqueYear.equals(year)){
                    if(objectWorkDate.getEndDate().before(new Date())){
                        completed++;
                    }else {
                        underConstruction++;
                    }
                }
            }
            overallForYears.add(OverallForYear.builder()
                    .year(uniqueYear)
                    .completed(completed)
                    .underConstruction(underConstruction)
                    .overall(completed + underConstruction)
                    .build());
        }
        List<OverallForYear> resp = new ArrayList<>();
        for(OverallForYear overallForYear: overallForYears){
            if(overallForYear.getYear().equals("2020") || overallForYear.getYear().equals("2021") || overallForYear.getYear().equals("2022")){
                resp.add(overallForYear);
            }
        }
        return resp;
    }


    public List<ObjectWorkDate> getAllWorkDatesByRegion(Integer id){
        List<Objects> objects = objectsRepo.findAllByRegion(id);

        List<ObjectWorkDate> objectWorkDates = new ArrayList<>();
        for(Objects objects1: objects){
            objectWorkDates.add(ObjectWorkDate.builder()
                    .id(objects1.getId())
                    .objectName(objects1.getObjectName())
                    .localityId(objects1.getLocalityId())
                    .lastUpdatedOn(objects1.getLastUpdatedOn())
                    .companyId(objects1.getCompanyId())
                    .endDate(objectsRepo.getEndDateForObject(objects1.getId()))
                    .build());
        }

        return objectWorkDates;
    }

    public List<ObjectsDto> getObjectsByCompanyAndLocality(String mtCode, Integer companyId, Integer localityId){
        List<ObjectsDto> objectsDtos = new ArrayList<>();
        List<Objects> objects = objectsRepo.findAllByCompanyAndLocality(companyId, localityId);

        for(Objects object: objects){
            ObjectsDto objectsDto = ObjectsDto.builder()
                    .id(object.getId())
                    .objectName(object.getObjectName())
                    .companyId(object.getCompanyId())
                    .localityId(object.getLocalityId())
                    .lastUpdatedOn(object.getLastUpdatedOn())
                    .build();

            // return objectInYearDtos

            objectsDtos.add(objectsDto);
        }

        return objectsDtos;
    }


//    public List<ObjectInYearDto> getObjectInYearDtos(String mtCode, Integer objectId){
//        List<ObjectInYearDto> objectInYearDtos = new ArrayList<>();
//        OverallVolumeAndPrice overallVolumeAndPrice = resourcesService.getOverallForObject(mtCode, objectId);
//
//
////        ObjectWorkDate objectWorkDate = ObjectWorkDate.builder()
////                .id(objects.getId())
////                .companyId(objects.getCompanyId())
////                .lastUpdatedOn(objects.getLastUpdatedOn())
////                .localityId(objects.getLocalityId())
////                .objectName(objects.getObjectName())
////                .endDate(objectsRepo.getEndDateForObject(objects.getId()))
////                .build();
////
////        workDates.add(objectWorkDate);
////        DateFormat dateFormat = new SimpleDateFormat("yyyy");
////        years.add(dateFormat.format(objectWorkDate.getEndDate()));
//
//        return null;
//
//
//    }
}
