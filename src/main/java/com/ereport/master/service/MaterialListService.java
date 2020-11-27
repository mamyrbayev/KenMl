package com.ereport.master.service;

import com.ereport.master.domain.MaterialList;
import com.ereport.master.domain.Report;
import com.ereport.master.repository.MaterialListRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MaterialListService {
    private final MaterialListRepo materialListRepo;
    private final ReportService reportService;

    public MaterialListService(MaterialListRepo materialListRepo, ReportService reportService) {
        this.materialListRepo = materialListRepo;
        this.reportService = reportService;
    }

    public List<MaterialList> findAll() {
        return materialListRepo.findAllByDeletedAtIsNull();
    }

    public MaterialList add(Long id, MaterialList materialList) {
        return materialListRepo.save(materialList);
    }

    public MaterialList findId(Long id) {
        return materialListRepo.findByIdAndDeletedAtIsNull(id);
    }

    public String update(Long id, String materialName) {
        MaterialList materialList = materialListRepo.findByIdAndDeletedAtIsNull(id);
        materialList.setMaterialName(materialName);
        materialListRepo.save(materialList);
        return "updated";
    }

    public void delete(Long id) {
        MaterialList materialList = materialListRepo.findByIdAndDeletedAtIsNull(id);
        Date date = new Date();
        materialList.setDeletedAt(date);
        materialListRepo.save(materialList);
    }
}
