package com.ereport.master.repository;


import com.ereport.master.domain.MaterialList;
import com.ereport.master.domain.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepo extends JpaRepository<Report, Long> {

    @Query(value = "SELECT *\n" +
            "from report\n" +
            "where deleted_at is null", nativeQuery = true)
    List<Report> findAllReportsByDeletedAtIsNull();

    @Query(value = "SELECT *\n" +
            "from report\n" +
            "where id = :id\n" +
            "and deleted_at is null", nativeQuery = true)
    Report findByIdAndDeletedAtIsNull(Long id);
}
