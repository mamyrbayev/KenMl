package com.ereport.master.kenML.service;

import com.ereport.master.kenML.domain.Companies;
import com.ereport.master.kenML.repository.CompaniesRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompaniesService {
    private final CompaniesRepo companiesRepo;

    public CompaniesService(CompaniesRepo companiesRepo) {
        this.companiesRepo = companiesRepo;
    }

    public List<Companies> findAllByLocalityId(Integer localityId){
        return companiesRepo.findAllByLocalityId(localityId);
    }
}
