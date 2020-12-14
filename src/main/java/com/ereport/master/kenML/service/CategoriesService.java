package com.ereport.master.kenML.service;

import com.ereport.master.kenML.domain.Categories;
import com.ereport.master.kenML.domain.Companies;
import com.ereport.master.kenML.domain.ReportCategories;
import com.ereport.master.kenML.domain.Reports;
import com.ereport.master.kenML.domain.dto.CategoryResponse;
import com.ereport.master.kenML.repository.CategoriesRepo;
import com.ereport.master.kenML.repository.ReportCategoriesRepo;
import com.ereport.master.kenML.repository.ReportsRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriesService {

    private final CategoriesRepo categoriesRepo;
    private final CompaniesService companiesService;
    private final ReportCategoriesRepo reportCategoriesRepo;

    public CategoriesService(CategoriesRepo categoriesRepo, CompaniesService companiesService, ReportCategoriesRepo reportCategoriesRepo) {
        this.categoriesRepo = categoriesRepo;
        this.companiesService = companiesService;
        this.reportCategoriesRepo = reportCategoriesRepo;
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



    public String deleteCategory(Integer id){
        Categories categories = findById(id);
        if(categories != null){
            List<Companies> companies = companiesService.findAllCompaniesByCategoryId(id);
            for(Companies company: companies){
                company.setCategoryID(null);
                companiesService.update(company);
            }
            try{
                reportCategoriesRepo.deleteAllByCategoryIdd(id);
            }catch (Exception e){

            }
            try{
                categoriesRepo.deleteCategoriesId(id);
            }catch (Exception e){

            }
            return "deleted";
        }else {
            return "Category" + id + "does not exists";
        }
    }
}
