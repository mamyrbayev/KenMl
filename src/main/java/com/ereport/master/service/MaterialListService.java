package com.ereport.master.service;

import com.ereport.master.domain.MaterialList;
import com.ereport.master.domain.Report;
import com.ereport.master.exceptions.ErrorCode;
import com.ereport.master.exceptions.ServiceException;
import com.ereport.master.repository.MaterialListRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MaterialListService {
    private final MaterialListRepo materialListRepo;
    private final ReportService reportService;

    public MaterialListService(MaterialListRepo materialListRepo, ReportService reportService) {
        this.materialListRepo = materialListRepo;
        this.reportService = reportService;
    }

    public List<MaterialList> findAll() {
        return materialListRepo.findAllByDeletedAtIsNull();
    }

    public MaterialList add(MaterialList materialList) throws ServiceException {
        if(materialList.getId() == null){
            return materialListRepo.save(materialList);
        }else {
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("id is not null")
                    .build();
        }
    }

    public MaterialList findId(Long id) {
        return materialListRepo.findByIdAndDeletedAtIsNull(id);
    }

    public MaterialList update(MaterialList materialList) throws ServiceException {
        if(materialList.getId() != null){
            return materialListRepo.save(materialList);
        }else {
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("id is not null")
                    .build();
        }
    }

    public void delete(Long id) {
        MaterialList materialList = materialListRepo.findByIdAndDeletedAtIsNull(id);
        Date date = new Date();
        materialList.setDeletedAt(date);
        materialListRepo.save(materialList);
    }
}
