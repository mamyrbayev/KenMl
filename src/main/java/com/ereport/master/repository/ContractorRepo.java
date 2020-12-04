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
}
