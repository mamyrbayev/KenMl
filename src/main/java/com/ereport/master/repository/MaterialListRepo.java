package com.ereport.master.repository;


import com.ereport.master.domain.Category;
import com.ereport.master.domain.MaterialList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialListRepo extends JpaRepository<MaterialList, Long> {


    List<MaterialList> findAllByDeletedAtIsNull();

    MaterialList findByIdAndDeletedAtIsNull(Long id);
}
