package com.ereport.master.kenML.service;

import com.ereport.master.kenML.domain.Objects;
import com.ereport.master.kenML.domain.Regions;
import com.ereport.master.kenML.domain.dto.*;
import com.ereport.master.kenML.repository.ObjectsRepo;
import com.ereport.master.kenML.repository.RegionsRepo;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegionsService {
    private final RegionsRepo regionsRepo;
    private final ObjectService objectService;
    private final ObjectsRepo objectsRepo;

    public RegionsService(RegionsRepo regionsRepo, ObjectService objectService, ObjectsRepo objectsRepo) {
        this.regionsRepo = regionsRepo;
        this.objectService = objectService;
        this.objectsRepo = objectsRepo;
    }

    public List<Regions> getAll(){
        return regionsRepo.findAll();
    }

    public List<OverallForYearByRegion> getOverallForYear(){

        List<OverallForYearByRegion> overallForYearByRegions = new ArrayList<>();

        List<Regions> regionsList = getAll();
        List<RegionsWorkDate> regionsWorkDates = new ArrayList<>();

        List<String> years = new ArrayList<>();
        List<Objects> objectsList = objectService.getAll();
        for(Objects objects: objectsList){
            DateFormat dateFormat = new SimpleDateFormat("yyyy");
            Date date = objectsRepo.getEndDateForObject(objects.getId());
            if(date != null){
                years.add(dateFormat.format(date));
            }
        }

        List<String> uniqueYears = new ArrayList<>();
        uniqueYears = years.stream().distinct().collect(Collectors.toList());

        for(String uniqueYear: uniqueYears){
            List<OverallByRegion> overallByRegions = new ArrayList<>();
            for(Regions regions: regionsList){
                int completed = 0;
                int underConstruction = 0;

                List<ObjectWorkDate> objectWorkDates = objectService.getAllWorkDatesByRegion(regions.getId());
                for(ObjectWorkDate objectWorkDate: objectWorkDates){
                    DateFormat dateFormat = new SimpleDateFormat("yyyy");
                    if(objectWorkDate.getEndDate() != null){
                        String objectYear = dateFormat.format(objectWorkDate.getEndDate());
                        if(uniqueYear.equals(objectYear)){
                            if(objectWorkDate.getEndDate().before(new Date())){
                                completed++;
                            }else {
                                underConstruction++;
                            }
                        }
                    }
                }
                if(regions.getName() != null){
                    if(completed + underConstruction != 0){
                        OverallByRegion overallByRegion = OverallByRegion.builder()
                                .regionName(regions.getName())
                                .regionId(regions.getId())
                                .completed(completed)
                                .underConstruction(underConstruction)
                                .overall(completed + underConstruction)
                                .build();
                        overallByRegions.add(overallByRegion);
                    }
                }

            }
            OverallForYearByRegion overallForYearByRegion = OverallForYearByRegion.builder()
                    .year(uniqueYear)
                    .overallByRegions(overallByRegions)
                    .build();
            overallForYearByRegions.add(overallForYearByRegion);
        }

        List<OverallForYearByRegion> resp = new ArrayList<>();
        for(OverallForYearByRegion o: overallForYearByRegions){
            if(o != null){
                if(o.getYear().equals("2020") || o.getYear().equals("2021") || o.getYear().equals("2022")){
                    resp.add(o);
                }
            }
        }

        return resp;
    }

}
