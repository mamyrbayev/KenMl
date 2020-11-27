package com.ereport.master.repository;


import com.ereport.master.domain.Category;
import com.ereport.master.domain.Publications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {


    List<Category> findAllByDeletedAtIsNull();

    Category findByIdAndDeletedAtIsNull(Long id);
}
