package com.ereport.master.kenML.repository;


import com.ereport.master.kenML.domain.Categories;
import com.ereport.master.kenML.domain.Reports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CategoriesRepo extends JpaRepository<Categories, Integer>{
    @Query(value = "exec [kenml].[Booklet_SaveCategories] @Name = :name, @Description = :description,  "+
            "@CreatedBy = :createdBy, @CreatedOn = :createdAt, @LastUpdatedBy = :updatedBy, " +
            "@LastUpdatedOn = :updatedAt", nativeQuery = true)
    Categories add(String name, String description,
                Integer createdBy, Date createdAt, Integer updatedBy, Date updatedAt);


    @Query(value = "exec [kenml].[Booklet_FindAllCategoriesByDeletedAtIsNull]", nativeQuery = true)
    List<Categories> findAllCategoriesByDeletedAtIsNull();

    @Query(value = "exec [kenml].[Booklet_FindCategoryByIdAndDeletedAtIsNull] @CategoriesID = :id", nativeQuery = true)
    Categories findCategoriesByIdAndDeletedAtIsNull(Integer id);


    @Query(value = "exec [kenml].[Booklet_UpdateCategories] @ID = :id, @Name = :name, @Description = :description,  " +
            "@LastUpdatedBy = :updatedBy, @LastUpdatedOn = :updatedAt", nativeQuery = true)
    Categories update(Integer id, String name, String description, Integer updatedBy, Date updatedAt);



}
