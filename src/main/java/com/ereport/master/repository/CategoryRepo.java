package com.ereport.master.repository;


import com.ereport.master.domain.Category;
import com.ereport.master.domain.Publications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

    @Query(value = "SELECT *\n" +
            "from category\n" +
            "where deleted_at is null", nativeQuery = true)
    List<Category> findAllByDeletedAtIsNull();

    @Query(value = "SELECT *\n" +
            "from category\n" +
            "where id = :id\n" +
            "and deleted_at is null", nativeQuery = true)
    Category findByIdAndDeletedAtIsNull(Long id);

    @Query(value = "select *\n" +
            "    from category c\n" +
            "    where c.id in(\n" +
            "            select rc.category_id\n" +
            "            from report_category rc\n" +
            "            where rc.report_id = :id\n" +
            "    )", nativeQuery = true)
    List<Category> findAllByReportId(Long id);





}
