package com.ereport.master.kenML.repository;

import com.ereport.master.kenML.domain.Companies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CompaniesRepo extends JpaRepository<Companies, Integer> {



    @Query(value = "exec [kenml].[Booklet_FindAllCompaniesByLocalityId] @LocalityID = :localityId", nativeQuery = true)
    List<Companies> findAllByLocalityId(Integer localityId);



    @Query(value = "exec [kenml].[Booklet_FindAllCompaniesByDeletedAtIsNull]", nativeQuery = true)
    List<Companies> findAllCompaniesByDeletedAtIsNull();

    @Query(value = "exec [kenml].[Booklet_FindCompanyByIdAndDeletedAtIsNull] @CompanyID = :id", nativeQuery = true)
    Companies findCompaniesByIdAndDeletedAtIsNull(Integer id);


    @Query(value = "exec [kenml].[Booklet_UpdateCompanies] @ID = :id, @LastUpdatedOn = :lastUpdatedOn, @BIN = :bin, " +
            "@Title = :title, @DirectorName = :directorName, " +
            "@DirectorPhone = :directorPhone, @EmailAddress = :emailAddress, " +
            "@PhysicalAddress = :physicalAddress, @CategoryID = :categoryID, " +
            "@ContactName = :contactName, @ContactPhone = :contactPhone ", nativeQuery = true)
    Companies update(Integer id, Date lastUpdatedOn, String bin, String title,
                     String directorName, String directorPhone, String emailAddress,
                     String physicalAddress, Integer categoryID, String contactName,
                     String contactPhone);


    @Query(value = "SELECT ID, LastUpdatedOn as updated_at, BIN as bin, Title as title, DirectorName as director_name,\n" +
            "                                               DirectorPhone as director_phone, EmailAddress as email_address, PhysicalAddress as physical_address,\n" +
            "                                               CategoryID, ContactName as contact_name, ContactPhone as contact_phone\n" +
            "FROM [kenml].[Companies]\n" +
            "WHERE CategoryID in(\n" +
            "    SELECT ReportCategories.CategoryID\n" +
            "    FROM [kenml].[ReportCategories]\n" +
            "    WHERE ReportID = :reportId\n" +
            "    )", nativeQuery = true)
    List<Companies> findAllCompaniesByReportId(Integer reportId);

    @Query(value = "exec [kenml].[Booklet_FindAllCompaniesByCategoryId] @CategoriesID = :id", nativeQuery = true)
    List<Companies> findAllCompaniesByCategoryId(Integer id);

}
