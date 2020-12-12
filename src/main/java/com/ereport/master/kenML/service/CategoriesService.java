package com.ereport.master.kenML.service;

import com.ereport.master.domain.Category;
import com.ereport.master.domain.dto.CategoryDTO;
import com.ereport.master.kenML.domain.Categories;
import com.ereport.master.kenML.domain.dto.CategoryResponse;
import com.ereport.master.kenML.repository.CategoriesRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


    public List<Categories> findAllByReportId(Integer id) {
        return categoriesRepo.findAllCategoriesByReportId(id);
    }


    public List<CategoryResponse> findAllByReportIdV2(Integer id) {
        List<CategoryResponse> resp = new ArrayList<>();
        List<Categories> categories =  findAllByReportId(id);
        for(Categories category: categories){
            resp.add(CategoryResponse.builder()
                    .categoryName(category.getName())
                    .description(category.getDescription())
//                    .contractors(contractorService.getAllByCategory(category.getId()))
                    .build());
        }
        return resp;
    }
}
