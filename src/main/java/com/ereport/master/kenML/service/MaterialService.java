package com.ereport.master.kenML.service;

import com.ereport.master.kenML.domain.Material;
import com.ereport.master.kenML.repository.MaterialRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {

    private final MaterialRepo materialRepo;

    public MaterialService(MaterialRepo materialRepo) {
        this.materialRepo = materialRepo;
    }

    public List<Material> getAllMaterialsByOwner(Long mtOwner){
        return materialRepo.findAllByOwner(mtOwner);
    }
}
