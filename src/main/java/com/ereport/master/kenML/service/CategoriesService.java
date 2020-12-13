package com.ereport.master.kenML.service;

import com.ereport.master.kenML.domain.Categories;
import com.ereport.master.kenML.domain.Companies;
import com.ereport.master.kenML.domain.dto.CategoryResponse;
import com.ereport.master.kenML.repository.CategoriesRepo;
import com.ereport.master.kenML.repository.CompaniesRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriesService {

    private final CategoriesRepo categoriesRepo;
    private final CompaniesService companiesService;
    private final CompaniesRepo companiesRepo;

    public CategoriesService(CategoriesRepo categoriesRepo, CompaniesService companiesService, CompaniesRepo companiesRepo) {
        this.categoriesRepo = categoriesRepo;
        this.companiesService = companiesService;
        this.companiesRepo = companiesRepo;
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
                    .contractors(companiesService.findAllCompaniesByCategoryId(category.getId()))
                    .build());
        }
        return resp;
    }



    public void deleteCategory(Integer id){
        List<Companies> companies = companiesService.findAllCompaniesByCategoryId(id);
        for(Companies company: companies){
            company.setCategoryID(null);
            companiesService.update(company);
        }
        try{
            categoriesRepo.deleteCategoriesId(id);
        }catch (Exception e){

        }
    }
}
