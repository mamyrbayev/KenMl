package com.ereport.master.service;

import com.ereport.master.domain.Category;
import com.ereport.master.domain.dto.CategoryDTO;
import com.ereport.master.exceptions.ErrorCode;
import com.ereport.master.exceptions.ServiceException;
import com.ereport.master.repository.CategoryRepo;
import com.ereport.master.repository.ContractorRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepo categoryRepo;
    private final ContractorService contractorService;

    public CategoryService(CategoryRepo categoryRepo, ContractorService contractorService) {
        this.categoryRepo = categoryRepo;
        this.contractorService = contractorService;
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
        if(category.getId() != null){
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


    public List<Category> findAllByReportId(Long id) {
        return categoryRepo.findAllByReportId(id);
    }


    public List<CategoryDTO> findAllByReportIdV2(Long id) {
        List<CategoryDTO> resp = new ArrayList<>();
        List<Category> categories =  categoryRepo.findAllByReportId(id);
        for(Category category: categories){
            resp.add(CategoryDTO.builder()
                    .CategoryName(category.getCategoryName())
                    .description(category.getDescription())
                    .contractors(contractorService.getAllByCategory(category.getId()))
                    .build());
        }
        return resp;
    }


}
