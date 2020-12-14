package com.ereport.master.kenML.repository;


import com.ereport.master.kenML.domain.Categories;
import com.ereport.master.kenML.domain.ReportMaterials;
import com.ereport.master.kenML.domain.Reports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReportMaterialsRepo extends JpaRepository<ReportMaterials, Integer> {

    @Query(value = "exec [kenml].[Booklet_SaveReportMaterials] @ReportID = :id, @MaterialCode = :materialCode  ", nativeQuery = true)
    ReportMaterials add(Integer id, String materialCode);


    @Query(value = "exec [kenml].[Booklet_FindAllReportMaterialsByDeletedAtIsNull]", nativeQuery = true)
    List<ReportMaterials> findAllReportMaterialsByDeletedAtIsNull();


    @Query(value = "DELETE from kenml.ReportMaterials", nativeQuery = true)
    void deleteraws();

}
