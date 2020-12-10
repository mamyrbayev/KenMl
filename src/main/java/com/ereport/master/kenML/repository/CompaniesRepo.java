package com.ereport.master.kenML.repository;

import com.ereport.master.kenML.domain.Companies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompaniesRepo extends JpaRepository<Companies, Integer> {

    @Query(value = "select *\n" +
            "from kenml.Companies c\n" +
            "where c.ID in(\n" +
            "    select o.CompanyID\n" +
            "    from kenml.Objects o\n" +
            "    where o.LocalityID = ?\n" +
            "    )", nativeQuery = true)
    List<Companies> findAllByLocalityId(Integer localityId);


//    Query query = session.createSQLQuery("CALL GetAllFoos()").addEntity(Foo.class);
//    @Query(value = "CALL Booklet_FindAllCompaniesByLocalityId()", nativeQuery = true)
//    List<Companies> findAllByLocalityId(Integer localityId);
}
