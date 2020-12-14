package com.ereport.master.kenML.repository;

import com.ereport.master.kenML.domain.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRepo extends JpaRepository<Material, Integer> {




    @Query(value = "exec [kenml].[Booklet_FindAllMaterialByOwner] @MT_OWNER = :mtOwner", nativeQuery = true)
    List<Material> findAllByOwner(Long mtOwner);

    @Query(value = "exec [kenml].[Booklet_FindAllMaterialByMaterialCode] @MT_CODE = :mtCode", nativeQuery = true)
    Material findByMaterialCode(String mtCode);

}
