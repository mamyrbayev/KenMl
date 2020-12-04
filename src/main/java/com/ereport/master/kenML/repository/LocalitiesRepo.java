package com.ereport.master.kenML.repository;

import com.ereport.master.kenML.domain.Localities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalitiesRepo extends JpaRepository<Localities, Integer> {

    @Query(value = "select *\n" +
            "from kenml.Localities\n" +
            "where ID in (\n" +
            "    SELECT o.LocalityID\n" +
            "    FROM [kenml].[Objects] o\n" +
            "    WHERE o.Id in(\n" +
            "        SELECT f.ObjectID\n" +
            "        from  kenml.Files f\n" +
            "        where f.ID in (\n" +
            "            select fs.FileID\n" +
            "            from kenml.FileSections fs\n" +
            "            where fs.ID in (\n" +
            "                SELECT r.FileSectionID\n" +
            "                FROM [kenml].[Resources] r\n" +
            "                WHERE ResourceType = 2\n" +
            "                  and CodeSNB = ?\n" +
            "            )\n" +
            "        )\n" +
            "    )\n" +
            ")", nativeQuery = true)
    List<Localities> findAllByMaterialCode(String codeSNB);
}
