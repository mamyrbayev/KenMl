package com.ereport.master.kenML.repository;

import com.ereport.master.kenML.domain.Objects;
import com.ereport.master.kenML.domain.Regions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RegionsRepo extends JpaRepository<Regions, Integer> {



    @Query(value = "exec [kenml].[Booklet_FindAllRegions]", nativeQuery = true)
    List<Regions> findAll();
}
