package com.ereport.master.repository;


import com.ereport.master.domain.Category;
import com.ereport.master.domain.MaterialList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialListRepo extends JpaRepository<MaterialList, Long> {

    @Query(value = "SELECT *\n" +
            "from material_list\n" +
            "where deleted_at is null", nativeQuery = true)
    List<MaterialList> findAllByDeletedAtIsNull();

    @Query(value = "SELECT *\n" +
            "from material_list\n" +
            "where id = :id\n" +
            "and deleted_at is null", nativeQuery = true)
    MaterialList findByIdAndDeletedAtIsNull(Long id);
}
