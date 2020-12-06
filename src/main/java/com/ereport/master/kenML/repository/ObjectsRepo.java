package com.ereport.master.kenML.repository;

import com.ereport.master.kenML.domain.Objects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ObjectsRepo extends JpaRepository<Objects, Integer> {

    @Query(value = "SELECT *\n" +
            "FROM [kenml].[Objects]", nativeQuery = true)
    List<Objects> findAll();

    @Query(value = "SELECT MAX(fs.EndDate)\n" +
            "FROM  [kenml].[FileSections] fs\n" +
            "WHERE fs.FileID in (\n" +
            "    SELECT f.ID\n" +
            "    FROM  [kenml].[Files] f\n" +
            "    WHERE f.ObjectID = ?\n" +
            ")", nativeQuery = true)
    Date getEndDateForObject(Integer id);


    @Query(value = "select *\n" +
            "from [kenml].Objects o\n" +
            "where o.LocalityID in (\n" +
            "    select l.ID\n" +
            "    from [kenml].Localities l\n" +
            "    where l.RegionID in (\n" +
            "        select r.ID\n" +
            "        from [kenml].Regions r\n" +
            "        where r.ID = ?\n" +
            "    )\n" +
            ")", nativeQuery = true)
    List<Objects> findAllByRegion(Integer id);



    @Query(value = "select *\n" +
            "from kenml.Objects\n" +
            "where CompanyID = ?\n" +
            "and LocalityID = ?", nativeQuery = true)
    List<Objects> findAllByCompanyAndLocality(Integer companyId, Integer localityId);

}
