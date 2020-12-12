package com.ereport.master.kenML.repository;

import com.ereport.master.kenML.domain.ReportCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportCategoriesRepo extends JpaRepository<ReportCategories, Integer> {


    @Query(value = "INSERT INTO [kenml].[ReportCategories](ReportID, CategoryID)\n" +
            "VALUES (:reportId, :categoryId)", nativeQuery = true)
    void add(Integer reportId, Integer categoryId);


    @Query(value = "DELETE FROM [kenml].[ReportCategories]\n" +
            "where ReportID = :reportId", nativeQuery = true)
    void deleteAllByReportIdd(Integer reportId);



}
