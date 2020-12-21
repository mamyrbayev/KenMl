package com.ereport.master.kenML.service;

import com.ereport.master.kenML.domain.FileSections;
import com.ereport.master.kenML.domain.Objects;
import com.ereport.master.kenML.domain.dto.*;
import com.ereport.master.kenML.repository.FileSectionRepo;
import com.ereport.master.kenML.repository.ObjectsRepo;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ObjectService {
    private final ObjectsRepo objectsRepo;
    private final ResourcesService resourcesService;
    private final FileSectionRepo fileSectionRepo;

    private static String[] monthKeys = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};

    public ObjectService(ObjectsRepo objectsRepo, ResourcesService resourcesService, FileSectionRepo fileSectionRepo) {
        this.objectsRepo = objectsRepo;
        this.resourcesService = resourcesService;
        this.fileSectionRepo = fileSectionRepo;
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
            if(objectWorkDate.getEndDate() != null){
                years.add(dateFormat.format(objectWorkDate.getEndDate()));
            }
        }

        List<String> uniqueYears = new ArrayList<>();

        uniqueYears = years.stream().distinct().collect(Collectors.toList());

        for(String uniqueYear: uniqueYears){
            int completed = 0;
            int underConstruction = 0;

            for(ObjectWorkDate objectWorkDate: workDates){
                DateFormat dateFormat = new SimpleDateFormat("yyyy");
                String year = null;
                if(objectWorkDate.getEndDate() != null){
                    year = dateFormat.format(objectWorkDate.getEndDate());
                }
                if(year != null){
                    if(uniqueYear.equals(year)){
                        if(objectWorkDate.getEndDate().before(new Date())){
                            completed++;
                        }else {
                            underConstruction++;
                        }
                    }
                }
            }
            int year = Integer.parseInt(uniqueYear);
            overallForYears.add(OverallForYear.builder()
                    .year(year)
                    .completed(completed)
                    .underConstruction(underConstruction)
                    .overall(completed + underConstruction)
                    .build());
        }
        List<OverallForYear> resp = new ArrayList<>();
        for(OverallForYear overallForYear: overallForYears){
            if(overallForYear.getYear() == 2020 || overallForYear.getYear() == 2021 || overallForYear.getYear() == 2022){
                resp.add(overallForYear);
            }
        }
        Collections.sort(resp, Comparator.comparingLong(OverallForYear::getYear));
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
            List<ObjectInYearDto> objectInYearDtos = getObjectInYearDtos(mtCode, object.getId());

            if(objectInYearDtos.size() > 0){
                ObjectsDto objectsDto = ObjectsDto.builder()
                        .id(object.getId())
                        .objectName(object.getObjectName())
                        .companyId(object.getCompanyId())
                        .localityId(object.getLocalityId())
                        .lastUpdatedOn(object.getLastUpdatedOn())
                        .objectInYearDtos(objectInYearDtos)
                        .build();
                objectsDtos.add(objectsDto);
            }
        }

        return objectsDtos;
    }


    public List<ObjectInYearDto> getObjectInYearDtos(String mtCode, Integer objectId){
        List<ObjectInYearDto> objectInYearDtos = new ArrayList<>();
//        OverallVolumeAndPrice overallVolumeAndPrice = resourcesService.getOverallForObject(mtCode, objectId);

        List<FileSections> fileSections = fileSectionRepo.findAllByObjectIdAndMaterial(objectId, mtCode);
        List<String> years = new ArrayList<>();

        for(FileSections fileSection: fileSections){
            DateFormat dateFormat = new SimpleDateFormat("yyyy");
            years.add(dateFormat.format(fileSection.getEndDate()));
        }

        List<String> uniqueYears = new ArrayList<>();
        uniqueYears = years.stream().distinct().collect(Collectors.toList());

        for(String year: uniqueYears){
            List<OverallVolumeAndPrice> volumeAndPrices = new ArrayList<>();
            for(FileSections fileSection: fileSections){
                DateFormat dateFormat = new SimpleDateFormat("yyyy");
                if(year.equals(dateFormat.format(fileSection.getEndDate()))){
                    volumeAndPrices.add(resourcesService.getOverallForFileSection(mtCode, fileSection.getId()));
                }
            }
            Float overallVolume = 0f;
            Float overallPrice = 0f;
            for(OverallVolumeAndPrice vp: volumeAndPrices){
                overallVolume += vp.getVolume();
                overallPrice += vp.getPrice();
            }


            List<MonthVolumePrice> monthVolumePrices = new ArrayList<>();
            for(String month: monthKeys){
                String date = year + ":" + month;
                List<OverallVolumeAndPrice> volumeAndPricesMonths = new ArrayList<>();
                for(FileSections fileSection: fileSections){
                    DateFormat dateFormat = new SimpleDateFormat("yyyy:MM");
                    if(date.equals(dateFormat.format(fileSection.getEndDate()))){
                        volumeAndPricesMonths.add(resourcesService.getOverallForFileSection(mtCode, fileSection.getId()));
                    }
                }
                Float overallVolumeMonths = 0f;
                Float overallPriceMonths = 0f;
                for(OverallVolumeAndPrice vp: volumeAndPricesMonths){
                    overallVolumeMonths += vp.getVolume();
                    overallPriceMonths += vp.getPrice();
                }
                MonthVolumePrice monthVolumePrice = MonthVolumePrice.builder()
                        .monthIndex(month)
                        .price(overallPriceMonths)
                        .volume(overallVolumeMonths)
                        .build();
                monthVolumePrices.add(monthVolumePrice);
            }

            ObjectInYearDto objectInYearDto = ObjectInYearDto.builder()
                    .year(year)
                    .overallPrice(overallPrice)
                    .overallVolume(overallVolume)
                    .monthVolumePrices(monthVolumePrices)
                    .build();

            objectInYearDtos.add(objectInYearDto);

        }

        return objectInYearDtos;
    }
}
