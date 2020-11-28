package com.ereport.master.service;

import com.ereport.master.domain.Category;
import com.ereport.master.exceptions.ErrorCode;
import com.ereport.master.exceptions.ServiceException;
import com.ereport.master.repository.CategoryRepo;
import com.ereport.master.repository.ContractorRepo;
import org.springframework.http.HttpStatus;
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

    public Category add(Category category) throws ServiceException {
        if(category.getId() == null){
            return categoryRepo.save(category);
        }else {
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("id is not null")
                    .build();
        }
    }

    public Category findId(Long id) {
        return categoryRepo.findByIdAndDeletedAtIsNull(id);
    }

    public Category update(Category category) throws ServiceException {
        if(category.getId() == null){
            return categoryRepo.save(category);
        }else {
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("id is null")
                    .build();
        }
    }

    public void delete(Long id){
        Category category = categoryRepo.findByIdAndDeletedAtIsNull(id);
        Date date = new Date();
        category.setDeletedAt(date);
        categoryRepo.save(category);
    }
}
