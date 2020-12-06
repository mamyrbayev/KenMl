package com.ereport.master.repository;

import com.ereport.master.domain.Contractor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractorRepo extends JpaRepository<Contractor, Long> {

    @Query(value = "SELECT *\n" +
            "from contractor\n" +
            "where deleted_at is null", nativeQuery = true)
    List<Contractor> findAllByDeletedAtIsNull();

    @Query(value = "SELECT *\n" +
            "from contractor\n" +
            "where id = :id\n" +
            "and deleted_at is null", nativeQuery = true)
    Contractor findByIdAndDeletedAtIsNull(Long id);

    List<Contractor> findAllByCategoryId(Long id);

    @Query(value = "select *\n" +
            "from contractor cn\n" +
            "where cn.category_id in (\n" +
            "    select c.id\n" +
            "    from category c\n" +
            "    where c.id in(\n" +
            "        select rc.category_id\n" +
            "        from report_category rc\n" +
            "        where rc.report_id = :id \n" +
            "    )\n" +
            "    )", nativeQuery = true)
    List<Contractor> findAllByReportId(Long id);
}
