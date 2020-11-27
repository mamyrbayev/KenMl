package com.ereport.master.repository;

import com.ereport.master.domain.Contractor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractorRepo extends JpaRepository<Contractor, Long> {


    List<Contractor> findAllByDeletedAtIsNull();

    Contractor findByIdAndDeletedAtIsNull(Long id);
}
