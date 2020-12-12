package com.ereport.master.repository;


import com.ereport.master.domain.Publications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationsRepoOld extends JpaRepository<Publications, Long> {

    @Query(value = "SELECT *\n" +
            "from publication\n" +
            "where deleted_at is null", nativeQuery = true)
    List<Publications> findAllByDeletedAtIsNull();

    @Query(value = "SELECT *\n" +
            "from publication\n" +
            "where id = :id\n" +
            "and deleted_at is null", nativeQuery = true)
    Publications findByIdAndDeletedAtIsNull(Long id);

    @Query(value = "SELECT *\n" +
            "from publication\n" +
            "where status = :status", nativeQuery = true)
    List<Publications> findAllByStatus(String status);

    List<Publications> findAllByDeletedAtIsNullAndReportId(Long reportId);

    @Query(value = "select *\n" +
            "from publication\n" +
            "where id in (\n" +
            "    select max(id)\n" +
            "    from publication\n" +
            "    where deleted_at is null\n" +
            "      and report_id = 1\n" +
            ");", nativeQuery = true)
    Publications findLastByDeletedAtIsNullAndReportId(Long reportId);
}
