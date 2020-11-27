package com.ereport.master.repository;


import com.ereport.master.domain.MaterialList;
import com.ereport.master.domain.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepo extends JpaRepository<Report, Long> {


    List<Report> findAllByDeletedAtIsNull();

    Report findByIdAndDeletedAtIsNull(Long id);
}
