package com.ereport.master.kenML.repository;

import com.ereport.master.domain.enums.Status;
import com.ereport.master.kenML.domain.Publications;
import com.ereport.master.kenML.domain.Reports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PublicationsRepo extends JpaRepository<Publications, Integer> {

    @Query(value = "exec [kenml].[Booklet_SavePublications] @ReportID = :reportId, @PublicationDate = :publicationDate, " +
            "@SendingDate = :sendingDate, @Status = :status, @AutoSending = :autoSending, @FilePath = :filePath, " +
            "@CreatedBy = :createdBy, @CreatedOn = :createdAt, @LastUpdatedBy = :updatedBy, @LastUpdatedOn = :updatedAt", nativeQuery = true)
    Publications add(Integer reportId, Date publicationDate, Date sendingDate, String status,
                Boolean autoSending, String filePath, Integer createdBy, Date createdAt,
                Integer updatedBy, Date updatedAt);


    @Query(value = "exec [kenml].[Booklet_FindAllPublicationsByDeletedAtIsNull]", nativeQuery = true)
    List<Publications> findAllPublicationsByDeletedAtIsNull();

    @Query(value = "exec [kenml].[Booklet_FindPublicationsByIdAndDeletedAtIsNull] @PublicationsID = :id", nativeQuery = true)
    Publications findPublicationsByIdAndDeletedAtIsNull(Integer id);


    @Query(value = "exec [kenml].[Booklet_FindPublicationsByDeletedAtIsNullAndReportId] @ReportID = :reportId", nativeQuery = true)
    List<Publications> findPublicationsByDeletedAtIsNullAndReportId(Integer reportId);

    @Query(value = "exec [kenml].[Booklet_FindAllPublicationsByStatus] @Status = :status", nativeQuery = true)
    List<Publications> findAllPublicationsByStatus(String status);


    @Query(value = "exec [kenml].[Booklet_FindLastPublicationsByDeletedAtIsNullAndReportId] @ReportID = :reportId", nativeQuery = true)
    Publications findLastPublicationsByDeletedAtIsNullAndReportId(Integer reportId);

}
