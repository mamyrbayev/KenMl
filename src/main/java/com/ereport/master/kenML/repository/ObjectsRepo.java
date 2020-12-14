package com.ereport.master.kenML.repository;

import com.ereport.master.kenML.domain.Objects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ObjectsRepo extends JpaRepository<Objects, Integer> {




    @Query(value = "exec [kenml].[Booklet_FindAllObjects]", nativeQuery = true)
    List<Objects> findAll();

    @Query(value = "exec [kenml].[Booklet_GetEndDateForObject] @ObjectID = :id", nativeQuery = true)
    Date getEndDateForObject(Integer id);

    @Query(value = "exec [kenml].[Booklet_FindAllObjectsByRegion] @RegionID = :id", nativeQuery = true)
    List<Objects> findAllByRegion(Integer id);

    @Query(value = "exec [kenml].[Booklet_FindAllObjectsByCompanyAndLocality] @CompanyID = :companyId, @LocalityID = :localityId", nativeQuery = true)
    List<Objects> findAllByCompanyAndLocality(Integer companyId, Integer localityId);

}
