package com.ereport.master.service;

import com.ereport.master.domain.Category;
import com.ereport.master.repository.CategoryRepo;
import com.ereport.master.repository.ContractorRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepo categoryRepo;
    private final ContractorRepo contractorRepo;

    public CategoryService(CategoryRepo categoryRepo, ContractorRepo contractorRepo ) {
        this.categoryRepo = categoryRepo;
        this.contractorRepo = contractorRepo;
    }


    public List<Category> findAll() {
        return categoryRepo.findAllByDeletedAtIsNull();
    }

    public Category add(Long id, Category category) {
        return categoryRepo.save(category);
    }

    public Category findId(Long id) {
        return categoryRepo.findByIdAndDeletedAtIsNull(id);
    }

    public String update(Long id, String CategoryName, String description) {
        Category category = categoryRepo.findByIdAndDeletedAtIsNull(id);
        category.setCategoryName(CategoryName);
        category.setDescription(description);
        categoryRepo.save(category);
        return "updated";
    }

    public void delete(Long id){
        Category category = categoryRepo.findByIdAndDeletedAtIsNull(id);
        Date date = new Date();
        category.setDeletedAt(date);
        categoryRepo.save(category);
    }
}
