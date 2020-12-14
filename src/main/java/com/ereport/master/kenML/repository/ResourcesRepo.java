package com.ereport.master.kenML.repository;

import com.ereport.master.kenML.domain.Resources;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourcesRepo extends JpaRepository<Resources, Integer> {




    @Query(value = "exec [kenml].[Booklet_FindAllResourcesByLocalityId] @LocalityID = :id", nativeQuery = true)
    List<Resources> findallByLocalityId(Integer id);

    @Query(value = "exec [kenml].[Booklet_FindAllResourcesByCodeSNBAndLocalityId] @CodeSNB = :codeSnb, @LocalityID = :id", nativeQuery = true)
    List<Resources> findallByCodeSNBAndLocalityId(String codeSnb, Integer id);

    @Query(value = "exec [kenml].[Booklet_FindAllResourcesForCompanyByCodeSNBAndLocalityId] @CodeSNB = :codeSnb, @CompanyID = :companyId, @LocalityID = :localityId", nativeQuery = true)
    List<Resources> findallByForCompany(String codeSnb, Integer companyId, Integer localityId);

    @Query(value = "exec [kenml].[Booklet_FindAllResourcesByObjectIdAndCodeSNB] @CodeSNB = :codeSnb, @ObjectID = :objectId", nativeQuery = true)
    List<Resources> findAllByObjectIdd(String codeSnb, Integer objectId);

    @Query(value = "exec [kenml].[Booklet_FindAllResourcesByFileSectionIdAndCodeSNB] @CodeSNB = :codeSnb, @FileSectionID = :fileSectionId", nativeQuery = true)
    List<Resources> findAllByFileSectionIdd(String codeSnb, Integer fileSectionId);
}
