package com.ereport.master.service;

import com.ereport.master.domain.Category;
import com.ereport.master.domain.Contractor;
import com.ereport.master.domain.dto.CategoryContractorsRequest;
import com.ereport.master.domain.dto.CategoryDTO;
import com.ereport.master.domain.dto.ContractorDTO;
import com.ereport.master.exceptions.ErrorCode;
import com.ereport.master.exceptions.ServiceException;
import com.ereport.master.kenML.domain.models.requests.CategoryRequest;
import com.ereport.master.kenML.domain.models.responses.CategoryResponse;
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

    public long add(CategoryRequest categoryRequest) throws ServiceException {
        System.out.println("CATEGORY REQUEST" + categoryRequest);
        return categoryRepo.save(categoryRequest.getCategoryName(),
                    categoryRequest.getDescription(), new Date(), new Date(),
                    null, null);
//        if(category.getId() == null){
//            return categoryRepo.save(category);
//        }else {
//            throw ServiceException.builder()
//                    .errorCode(ErrorCode.SYSTEM_ERROR)
//                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .message("id is not null")
//                    .build();
//        }
    }

    public Category findId(Long id) {
        return categoryRepo.findByIdAndDeletedAtIsNull(id);
    }

    public Category update(Category category) throws ServiceException {
        if (category.getId() != null) {
            return categoryRepo.save(category);
        } else {
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("id is null")
                    .build();
        }
    }

    public void delete(Long id) {
        Category category = categoryRepo.findByIdAndDeletedAtIsNull(id);
        Date date = new Date();
//        category.setDeletedAt(date);
        categoryRepo.save(category);
    }


    public List<Category> findAllByReportId(Long id) {
        return categoryRepo.findAllByReportId(id);
    }


    public List<CategoryDTO> findAllByReportIdV2(Long id) {
        List<CategoryDTO> resp = new ArrayList<>();
        List<Category> categories = categoryRepo.findAllByReportId(id);
        for (Category category : categories) {
            resp.add(CategoryDTO.builder()
                    .CategoryName(category.getCategoryName())
                    .description(category.getDescription())
                    .contractors(contractorService.getAllByCategory(category.getId()))
                    .build());
        }
        return resp;
    }


    public CategoryContractorsRequest addCategoriesWithContractors(CategoryContractorsRequest ccr) throws ServiceException {
        Category category = categoryRepo.save(Category.builder()
                .categoryName(ccr.getCategoryName())
                .description(ccr.getDescription())
                .build());

        for (ContractorDTO contractor : ccr.getContractors()) {
            contractorService.add(Contractor.builder()
                    .contractorName(contractor.getContractorName())
                    .bin(contractor.getBin())
                    .eMail(contractor.getEMail())
                    .phoneNumber(contractor.getPhoneNumber())
                    .category(category)
                    .build());
        }
        return ccr;
    }
}
