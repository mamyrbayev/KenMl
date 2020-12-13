package com.ereport.master.kenML.repository;

import com.ereport.master.kenML.domain.ReportCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportCategoriesRepo extends JpaRepository<ReportCategories, Integer> {


    @Query(value = "INSERT INTO [kenml].[ReportCategories](ReportID, CategoryID)\n" +
            "VALUES (:reportId, :categoryId)", nativeQuery = true)
    void add(Integer reportId, Integer categoryId);


    @Query(value = "DELETE FROM [kenml].[ReportCategories]\n" +
            "where ReportID = :reportId", nativeQuery = true)
    void deleteAllByReportIdd(Integer reportId);

    @Query(value = "DELETE FROM [kenml].[ReportCategories]\n" +
            "where CategoryID = :categoryId", nativeQuery = true)
    void deleteAllByCategoryIdd(Integer categoryId);

    @Query(value = "SELECT *\n" +
            "    FROM [kenml].[ReportCategories]\n" +
            "    WHERE CategoryID = :id", nativeQuery = true)
    List<ReportCategories> findAllReportCategoriesByCategoryId(Integer id);



}
