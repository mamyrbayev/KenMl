package com.ereport.master.kenML.repository;

import com.ereport.master.kenML.domain.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRepo extends JpaRepository<Material, Long> {

    @Query(value = "SELECT ROW_NUMBER() OVER(ORDER BY MT_LINK ASC) AS id, * \n" +
            "FROM [kenml].[Materials]\n" +
            "WHERE MT_OWNER = ?;", nativeQuery = true)
    List<Material> findAllByOwner(Long mtOwner);

    @Query(value = "select *\n" +
            "from kenml.Materials\n" +
            "where MT_CODE = ?", nativeQuery = true)
    Material findByMaterialCode(String mtCode);



}
