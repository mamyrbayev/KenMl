package com.ereport.master.kenML.repository;

import com.ereport.master.kenML.domain.Publications;
import com.ereport.master.kenML.domain.Reports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface PublicationsRepo extends JpaRepository<Publications, Integer> {

    @Query(value = "exec [kenml].[Booklet_SaveReports]", nativeQuery = true)
    Reports add();
}
