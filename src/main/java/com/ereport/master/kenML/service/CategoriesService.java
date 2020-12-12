package com.ereport.master.kenML.service;

import com.ereport.master.kenML.domain.Categories;
import com.ereport.master.kenML.repository.CategoriesRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService {

    private final CategoriesRepo categoriesRepo;

    public CategoriesService(CategoriesRepo categoriesRepo) {
        this.categoriesRepo = categoriesRepo;
    }

    public Categories save(Categories r){
        return categoriesRepo.add(r.getName(), r.getDescription(), r.getCreatedBy(), r.getCreatedAt(), r.getUpdatedBy(), r.getUpdatedAt());
    }

    public List<Categories> findAll(){
        return categoriesRepo.findAllCategoriesByDeletedAtIsNull();
    }

    public Categories findById(Integer id){
        return categoriesRepo.findCategoriesByIdAndDeletedAtIsNull(id);
    }


    public Categories update(Categories r){
        return categoriesRepo.update(r.getId(), r.getName(), r.getDescription(), r.getUpdatedBy(), r.getUpdatedAt());
    }
}
