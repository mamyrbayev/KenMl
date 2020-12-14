package com.ereport.master.kenML.repository;

import com.ereport.master.kenML.domain.Localities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalitiesRepo extends JpaRepository<Localities, Integer> {


    @Query(value = "exec [kenml].[Booklet_FindLocalitiesAllByMaterialCode] @CodeSNB = :codeSNB", nativeQuery = true)
    List<Localities> findAllByMaterialCode(String codeSNB);
}
