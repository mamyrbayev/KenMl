package com.ereport.master.service;


import com.ereport.master.domain.Contractor;
import com.ereport.master.exceptions.ErrorCode;
import com.ereport.master.exceptions.ServiceException;
import com.ereport.master.repository.ContractorRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ContractorService {
    private final ContractorRepo contractorRepo;

    public ContractorService(ContractorRepo contractorRepo) {
        this.contractorRepo = contractorRepo;
    }

    public List<Contractor> findAll() {
        return contractorRepo.findAllByDeletedAtIsNull();
    }

    public Contractor add(Contractor contractor) throws ServiceException {
        if(contractor.getId() == null){
            return contractorRepo.save(contractor);
        }else {
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("id is not null")
                    .build();
        }
    }

    public Contractor findId(Long id){
        return contractorRepo.findByIdAndDeletedAtIsNull(id);
    }

    public Contractor update(Contractor contractor) throws ServiceException {
        if(contractor.getId() != null){
            return contractorRepo.save(contractor);
        }else {
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("id is null")
                    .build();
        }
    }

    public void delete(Long id){
        Contractor contractor = contractorRepo.findByIdAndDeletedAtIsNull(id);
        Date date = new Date();
        contractor.setDeletedAt(date);
        contractorRepo.save(contractor);
    }

}
