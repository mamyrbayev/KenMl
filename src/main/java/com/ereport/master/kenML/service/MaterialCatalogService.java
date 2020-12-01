package com.ereport.master.kenML.service;

import com.ereport.master.kenML.domain.MaterialCatalog;
import com.ereport.master.kenML.repository.MaterialCatalogRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialCatalogService {
    private final MaterialCatalogRepo materialCatalogRepo;

    public MaterialCatalogService(MaterialCatalogRepo materialCatalogRepo) {
        this.materialCatalogRepo = materialCatalogRepo;
    }

    public List<MaterialCatalog> getAllOtdels(){
        return materialCatalogRepo.findAllOtdels();
    }

    public List<MaterialCatalog> getAllSubByOwner(Long mcOwner){
        return materialCatalogRepo.findAllSubByOwner(mcOwner);
    }
}
