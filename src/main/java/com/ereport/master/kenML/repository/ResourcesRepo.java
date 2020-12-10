package com.ereport.master.kenML.repository;

import com.ereport.master.kenML.domain.Resources;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourcesRepo extends JpaRepository<Resources, Integer> {

    @Query(value = "select *\n" +
            "from kenml.Resources r\n" +
            "where ResourceType = 2\n" +
            "  and r.FileSectionID in(\n" +
            "    select fs.ID\n" +
            "    from kenml.FileSections fs\n" +
            "    where fs.FileID in (\n" +
            "        select f.ID\n" +
            "        from kenml.Files f\n" +
            "        where f.ObjectID in (\n" +
            "            select o.ID\n" +
            "            from kenml.Objects o\n" +
            "            where o.LocalityID = ?\n" +
            "            )\n" +
            "        )\n" +
            "    )", nativeQuery = true)
    List<Resources> findallByLocalityId(Integer id);

    @Query(value = "select *\n" +
            "from kenml.Resources r\n" +
            "where ResourceType = 2\n" +
            "  and CodeSNB = :codeSnb \n" +
            "  and r.FileSectionID in(\n" +
            "    select fs.ID\n" +
            "    from kenml.FileSections fs\n" +
            "    where fs.FileID in (\n" +
            "        select f.ID\n" +
            "        from kenml.Files f\n" +
            "        where f.ObjectID in (\n" +
            "            select o.ID\n" +
            "            from kenml.Objects o\n" +
            "            where o.LocalityID = :id \n" +
            "            )\n" +
            "        )\n" +
            "    )", nativeQuery = true)
    List<Resources> findallByCodeSNBAndLocalityId(String codeSnb, Integer id);


//FindAllResourcesForCompanyByCodeSNBAndLocalityId
    @Query(value = "select *\n" +
            "from kenml.Resources r\n" +
            "where ResourceType = 2\n" +
            "  and CodeSNB = :codeSnb \n" +
            "  and r.FileSectionID in(\n" +
            "    select fs.ID\n" +
            "    from kenml.FileSections fs\n" +
            "    where fs.FileID in (\n" +
            "        select f.ID\n" +
            "        from kenml.Files f\n" +
            "        where f.ObjectID in (\n" +
            "            select o.ID\n" +
            "            from kenml.Objects o\n" +
            "            where o.CompanyID = :companyId \n" +
            "            and o.LocalityID = :localityId \n" +
            "        )\n" +
            "    )\n" +
            ")\n", nativeQuery = true)
    List<Resources> findallByForCompany(String codeSnb, Integer companyId, Integer localityId);

//FindAllResourcesByObjectIdAndCodeSNB
    @Query(value = "select *\n" +
            "from kenml.Resources r\n" +
            "where ResourceType = 2\n" +
            "  and CodeSNB = :codeSnb \n" +
            "  and r.FileSectionID in(\n" +
            "    select fs.ID\n" +
            "    from kenml.FileSections fs\n" +
            "    where fs.FileID in (\n" +
            "        select f.ID\n" +
            "        from kenml.Files f\n" +
            "        where f.ObjectID = :objectId \n" +
            "    )\n" +
            ")", nativeQuery = true)
    List<Resources> findAllByObjectIdd(String codeSnb, Integer objectId);


//FindAllResourcesByFileSectionIdAndCodeSNB
    @Query(value = "select *\n" +
            "from kenml.Resources r\n" +
            "where ResourceType = 2\n" +
            "  and CodeSNB = :codeSnb \n" +
            "  and r.FileSectionID = :fileSectionId", nativeQuery = true)
    List<Resources> findAllByFileSectionIdd(String codeSnb, Integer fileSectionId);
}
