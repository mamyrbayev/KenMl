package com.ereport.master.kenML.repository;

import com.ereport.master.kenML.domain.FileSections;
import com.ereport.master.kenML.domain.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileSectionRepo extends JpaRepository<FileSections, Integer> {



    @Query(value = "exec [kenml].[Booklet_FindAllFileSectionsByObjectIdAndMaterial] @ObjectID = :objectId, @CodeSNB = :codeSnb", nativeQuery = true)
    List<FileSections> findAllByObjectIdAndMaterial(Integer objectId, String codeSnb);


}
