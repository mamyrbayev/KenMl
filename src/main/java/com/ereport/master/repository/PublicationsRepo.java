package com.ereport.master.repository;


import com.ereport.master.domain.Publications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationsRepo extends JpaRepository<Publications, Long> {
    List<Publications> findAllByDeletedAtIsNull();
    Publications findByIdAndDeletedAtIsNull(Long id);
}
