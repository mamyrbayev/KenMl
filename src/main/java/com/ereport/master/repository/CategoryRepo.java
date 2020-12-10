package com.ereport.master.repository;


import com.ereport.master.domain.Category;
import com.ereport.master.domain.Publications;
import com.ereport.master.kenML.domain.models.responses.CategoryResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

//    @Query(value = "SELECT *\n" +
//            "from category\n" +
//            "where deleted_at is null", nativeQuery = true)
//    List<Category> findAllByDeletedAtIsNull();
//
//    @Query(value = "SELECT *\n" +
//            "from category\n" +
//            "where id = :id\n" +
//            "and deleted_at is null", nativeQuery = true)
//    Category findByIdAndDeletedAtIsNull(Long id);

//    @Query(value = "select *\n" +
//            "    from category c\n" +
//            "    where c.id in(\n" +
//            "            select rc.category_id\n" +
//            "            from report_category rc\n" +
//            "            where rc.report_id = :id\n" +
//            "    )", nativeQuery = true)
//    List<Category> findAllByReportId(Long id);


    @Query(value = "exec [kenml].[Booklet_FindAllCategoriesByDeletedAtIsNull]", nativeQuery = true)
    List<Category> findAllByDeletedAtIsNull();

    @Query(value = "exec [kenml].[Booklet_FindCategoryByIdAndDeletedAtIsNull] @CategoriesID = :id", nativeQuery = true)
    Category findByIdAndDeletedAtIsNull(Long id);

    @Query(value = "exec [kenml].[Booklet_FindAllCategoriesByReportId] @ReportId = :id", nativeQuery = true)
    List<Category> findAllByReportId(Long id);

    //    @Query(value = "exec [kenml].[Booklet_SaveCategories] @Name = :name, @Description = :description, @CreatedOn = :createdOn, LastUpdatedOn = :lastUpdatedOn, CreatedBy = :createdBy, LastUpdatedBy = :lastUpdatedBy ", nativeQuery = true)
    @Query(value = "exec [kenml].[Booklet_SaveCategories] :name, :description, :createdOn, :lastUpdatedOn, :createdBy, :lastUpdatedBy ", nativeQuery = true)
    long save(String name, String description, Date createdOn, Date lastUpdatedOn, Integer createdBy, Integer lastUpdatedBy);


}
