package com.ereport.master.repository;


import com.ereport.master.domain.Publications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationsRepo extends JpaRepository<Publications, Long> {

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
}
