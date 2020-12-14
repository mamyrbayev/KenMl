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

    public List<Companies> findAll(){
        return companiesRepo.findAllCompaniesByDeletedAtIsNull();
    }

    public Companies findById(Integer id){
        return companiesRepo.findCompaniesByIdAndDeletedAtIsNull(id);
    }


    public Companies update(Companies c){
        return companiesRepo.update(c.getId(), c.getLastUpdatedOn(), c.getBin(), c.getTitle(), c.getDirectorName(),
                c.getDirectorPhone(), c.getEmailAddress(), c.getPhysicalAddress(), c.getCategoryID(),
                c.getContactName(), c.getContactPhone());
    }

    public List<Companies> findAllCompaniesByCategoryId(Integer categoryId){
        return companiesRepo.findAllCompaniesByCategoryId(categoryId);
    }
}
