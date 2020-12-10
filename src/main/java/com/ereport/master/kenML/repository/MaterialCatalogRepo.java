package com.ereport.master.kenML.repository;

import com.ereport.master.kenML.domain.MaterialCatalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialCatalogRepo extends JpaRepository<MaterialCatalog, Long> {

//    @Query(value = "SELECT ROW_NUMBER() OVER(ORDER BY MC_CODE ASC) AS id, *\n" +
//            "FROM [kenml].[MaterialsCatalog]\n" +
//            "WHERE MC_LEVEL = 10;", nativeQuery = true)
//    List<MaterialCatalog> findAllOtdels();

    @Query(value = "exec [kenml].[Booklet_FindAllMaterialCatalogsByOtdels]", nativeQuery = true)
    List<MaterialCatalog> findAllOtdels();

//    @Query(value = "SELECT ROW_NUMBER() OVER(ORDER BY MC_CODE ASC) AS id, *\n" +
//            "FROM [kenml].[MaterialsCatalog]\n" +
//            "WHERE MC_OWNER = ?;", nativeQuery = true)
//    List<MaterialCatalog> findAllSubByOwner(Long mcOwner);

    @Query(value = "exec [kenml].[Booklet_FindAllMaterialCatalogsSubByOwner] @MC_OWNER = :mcOwner", nativeQuery = true)
    List<MaterialCatalog> findAllSubByOwner(Long mcOwner);

}
