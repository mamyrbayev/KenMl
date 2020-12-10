package com.ereport.master.kenML.repository;

import com.ereport.master.kenML.domain.FileSections;
import com.ereport.master.kenML.domain.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileSectionRepo extends JpaRepository<FileSections, Integer> {

//    @Query(value = "select *\n" +
//            "from kenml.FileSections fs\n" +
//            "where fs.StartDate is not null\n" +
//            "      and fs.FileID in(\n" +
//            "    select f.ID\n" +
//            "    from kenml.Files f\n" +
//            "    where f.ObjectID = ?\n" +
//            "    )\n" +
//            "and fs.ID in (\n" +
//            "    SELECT r.FileSectionID\n" +
//            "    from kenml.Resources r\n" +
//            "    where r.ResourceType = 2\n" +
//            "      and r.CodeSNB = ?\n" +
//            "    )\n", nativeQuery = true)
//    List<FileSections> findAllByObjectIdAndMaterial(Integer objectId, String codeSnb);


    @Query(value = "exec [kenml].[Booklet_FindAllFileSectionsByObjectIdAndMaterial] @ObjectID = :objectId, @CodeSNB = :codeSnb", nativeQuery = true)
    List<FileSections> findAllByObjectIdAndMaterial(Integer objectId, String codeSnb);


}
