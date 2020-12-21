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

    public Material getByMaterialCode(String mtCode){
//        System.out.println("MT CODE " + mtCode);
        Material material = materialRepo.findByMaterialCode(mtCode);
//        System.out.println("Material " + material);
        return material;
    }
}
