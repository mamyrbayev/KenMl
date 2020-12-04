package com.ereport.master.kenML.repository;

import com.ereport.master.kenML.domain.Resources;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourcesRepo extends JpaRepository<Resources, Integer> {

}
