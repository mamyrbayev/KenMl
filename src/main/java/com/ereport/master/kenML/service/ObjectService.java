package com.ereport.master.kenML.service;

import com.ereport.master.kenML.domain.FileSections;
import com.ereport.master.kenML.domain.Objects;
import com.ereport.master.kenML.domain.Resources;
import com.ereport.master.kenML.domain.dto.*;
import com.ereport.master.kenML.repository.FileSectionRepo;
import com.ereport.master.kenML.repository.ObjectsRepo;
import com.ereport.master.kenML.repository.ResourcesRepo;
import com.google.common.base.CharMatcher;
import org.springframework.stereotype.Service;

import java.text.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ObjectService {
    private final ObjectsRepo objectsRepo;
    private final ResourcesService resourcesService;
    private final ResourcesRepo resourcesRepo;
    private final FileSectionRepo fileSectionRepo;

    private static String[] monthKeys = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};

    public ObjectService(ObjectsRepo objectsRepo, ResourcesService resourcesService, ResourcesRepo resourcesRepo, FileSectionRepo fileSectionRepo) {
        this.objectsRepo = objectsRepo;
        this.resourcesService = resourcesService;
        this.resourcesRepo = resourcesRepo;
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
            if(overallForYear.getYear() == 2020 || overallForYear.getYear() == 2021){
                resp.add(overallForYear);
            }
        }
        boolean contains2020 = false;
        boolean contains2021 = false;

        for(OverallForYear overallForYear: resp){
            if(overallForYear.getYear() == 2020){
                contains2020 = true;
            }
            if(overallForYear.getYear() == 2021){
                contains2021 = true;
            }
        }
        if(!contains2020){
            OverallForYear forYear = new OverallForYear();
            forYear.setYear(2020);
            resp.add(forYear);
        }
        if(!contains2021){
            OverallForYear forYear = new OverallForYear();
            forYear.setYear(2021);
            resp.add(forYear);
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

    public List<ObjectsDto> getObjectsByCompanyAndLocality(String mtCode, Integer companyId, Integer localityId) throws ParseException {
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

    public List<ObjectInYearDto> getObjectInYearDtos(String mtCode, Integer objectId) throws ParseException {
        List<ObjectInYearDto> objectInYearDtos = new ArrayList<>();
//        OverallVolumeAndPrice overallVolumeAndPrice = resourcesService.getOverallForObject(mtCode, objectId);

        List<FileSections> fileSections = fileSectionRepo.findAllByObjectIdAndMaterial(objectId, mtCode);
        List<String> years = new ArrayList<>();

        for(FileSections fileSection: fileSections){
            DateFormat dateFormat = new SimpleDateFormat("yyyy");
            years.add(dateFormat.format(fileSection.getStartDate()));
        }

        List<String> uniqueYears = new ArrayList<>();
        uniqueYears = years.stream().distinct().collect(Collectors.toList());

        for(String year: uniqueYears){
            List<OverallVolumeAndPrice> volumeAndPrices = new ArrayList<>();
            List<FileSections> fileSectionsForYear = new ArrayList<>();
            for(FileSections fileSection: fileSections){
                DateFormat dateFormat = new SimpleDateFormat("yyyy");
                if(year.equals(dateFormat.format(fileSection.getStartDate()))){
                    volumeAndPrices.add(resourcesService.getOverallForFileSection(mtCode, fileSection.getId()));
                    fileSectionsForYear.add(fileSection);
                }
            }
            Float overallVolume = 0f;
            Float overallPrice = 0f;
            for(OverallVolumeAndPrice vp: volumeAndPrices){
                overallVolume += vp.getVolume();
                overallPrice += vp.getPrice();
            }

            if(overallPrice >= 1){
                overallPrice = (float) Math.round(overallPrice);
            }else {
                overallPrice = resourcesService.formatNumber(overallPrice);
            }
            if(overallVolume >= 1){
                overallVolume = (float) Math.round(overallVolume);
            }else {
                overallVolume = resourcesService.formatNumber(overallVolume);
            }

            boolean addMonthVolumePrice = false;
            List<MonthVolumePrice> monthVolumePrices = getMonthVolumePricesByYear(fileSectionsForYear, mtCode);
            for(MonthVolumePrice monthVolumePrice: monthVolumePrices){
                if(monthVolumePrice.getVolume() != 0f){
                    addMonthVolumePrice = true;
                    break;
                }
            }

            ObjectInYearDto objectInYearDto = ObjectInYearDto.builder()
                    .year(year)
                    .overallPrice(overallPrice)
                    .overallVolume(overallVolume)
                    .monthVolumePrices(monthVolumePrices)
                    .build();

            if(addMonthVolumePrice){
                objectInYearDtos.add(objectInYearDto);
            }

        }
        objectInYearDtos.sort(Comparator.comparing(ObjectInYearDto::getOverallVolume).reversed());
        return objectInYearDtos;
    }


    public List<MonthVolumePrice> getMonthVolumePricesByYear(List<FileSections> fileSections, String mtCode) throws ParseException {
        Map<String, Float> volumeByMonth = new HashMap<>();
        Date endDate = null;
        Float price = 0f;
        volumeByMonth.put("01", 0f);
        volumeByMonth.put("02", 0f);
        volumeByMonth.put("03", 0f);
        volumeByMonth.put("04", 0f);
        volumeByMonth.put("05", 0f);
        volumeByMonth.put("06", 0f);
        volumeByMonth.put("07", 0f);
        volumeByMonth.put("08", 0f);
        volumeByMonth.put("09", 0f);
        volumeByMonth.put("10", 0f);
        volumeByMonth.put("11", 0f);
        volumeByMonth.put("12", 0f);
        for(FileSections fileSection: fileSections){
            OverallVolumeAndPrice overallVolumeAndPrice = resourcesService.getOverallForFileSection(mtCode, fileSection.getId());

            List<Resources> resources = resourcesRepo.findAllByFileSectionIdd(mtCode, fileSection.getId());
            if(resources.size() > 0){
                price = resources.get(0).getPrice();
            }

            Calendar day1 = Calendar.getInstance();
            Calendar day2 = Calendar.getInstance();
            day1.setTime(fileSection.getStartDate());
            day2.setTime(fileSection.getEndDate());

            int daysBetween = Math.abs(day1.get(Calendar.DAY_OF_YEAR) - day2.get(Calendar.DAY_OF_YEAR));

            Float volumeInOneDay =  overallVolumeAndPrice.getVolume()/daysBetween;

            Date startDate = fileSection.getStartDate();
            endDate = fileSection.getStartDate();
            while (startDate.before(fileSection.getEndDate())){
                DateFormat dateFormat = new SimpleDateFormat("MM");
                volumeByMonth.put(dateFormat.format(startDate), volumeByMonth.get(dateFormat.format(startDate)) + volumeInOneDay);

                Calendar cal = Calendar.getInstance();
                cal.setTime(startDate);
                cal.add(Calendar.DAY_OF_WEEK, 1);
                startDate = cal.getTime();
            }
        }

        List<MonthVolumePrice> monthVolumePrices = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = "2020-01-01";
        Date date = sdf.parse(strDate);

        if(endDate != null){
            if(endDate.after(date)){
                for (String month: monthKeys){
                    Float overallVolume = volumeByMonth.get(month);
                    Float overallPrice = volumeByMonth.get(month) * price;
                    if(overallPrice >= 1){
                        overallPrice = (float) Math.round(overallPrice);
                    }else {
                        overallPrice = resourcesService.formatNumber(overallPrice);
                    }
                    if(overallVolume >= 1){
                        overallVolume = (float) Math.round(overallVolume);
                    }else {
                        overallVolume = resourcesService.formatNumber(overallVolume);
                    }
                    MonthVolumePrice monthVolumePrice = MonthVolumePrice.builder()
                            .monthIndex(month)
                            .price(overallPrice)
                            .volume(overallVolume)
                            .build();
                    monthVolumePrices.add(monthVolumePrice);
                }
            }
        }
        return monthVolumePrices;
    }

}
