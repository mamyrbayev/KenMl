package com.ereport.master.service;


import com.ereport.master.domain.Contractor;
import com.ereport.master.repository.ContractorRepo;
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

    public Contractor add(Long id, Contractor contractor) {
        return contractorRepo.save(contractor);
    }

    public Contractor findId(Long id){
        return contractorRepo.findByIdAndDeletedAtIsNull(id);
    }

    public String update(Long id, String contrName, String eMail, int phoneNum) {
        Contractor contractor = contractorRepo.findByIdAndDeletedAtIsNull(id);
        contractor.setContrName(contrName);
        contractor.setEMail(eMail);
        contractor.setPhoneNum(phoneNum);
        contractorRepo.save(contractor);
        return "updated";
    }

    public void delete(Long id){
        Contractor contractor = contractorRepo.findByIdAndDeletedAtIsNull(id);
        Date date = new Date();
        contractor.setDeletedAt(date);
        contractorRepo.save(contractor);
    }

}
