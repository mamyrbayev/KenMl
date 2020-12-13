package com.ereport.master.kenML.repository;

import com.ereport.master.kenML.domain.Reports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReportsRepo extends JpaRepository<Reports, Integer> {

    @Query(value = "exec [kenml].[Booklet_SaveReports] @Name = :name, @DaysOfPublications = :timeOfPublication, " +
            "@AutoSending = :autoSending, @Generate_on_Monday = :generateInMonday, " +
            "@Generate_on_Tuesday = :generateInTuesday, @Generate_on_Wednesday = :generateInWednesday, " +
            "@Generate_on_Thursday = :generateInThursday, @Generate_on_Friday = :generateInFriday, " +
            "@Generate_on_Saturday = :generateInSaturday, @Generate_on_Sunday = :generateInSunday, " +
            "@IconPath = :iconPath, @SendAfterTime = :sendAfterTime, @Publicate = :publicate, " +
            "@CreatedBy = :createdBy, @CreatedOn = :createdAt, @LastUpdatedBy = :updatedBy, " +
            "@LastUpdatedOn = :updatedAt", nativeQuery = true)
    Reports add(String name, String timeOfPublication, Boolean autoSending,
                Boolean generateInMonday, Boolean generateInTuesday, Boolean generateInWednesday,
                Boolean generateInThursday, Boolean generateInFriday, Boolean generateInSaturday,
                Boolean generateInSunday, String iconPath, Long sendAfterTime, Boolean publicate,
                Integer createdBy, Date createdAt, Integer updatedBy, Date updatedAt);


    @Query(value = "exec [kenml].[Booklet_FindAllReportsByDeletedAtIsNull]", nativeQuery = true)
    List<Reports> findAllReportsByDeletedAtIsNull();



    @Query(value = "exec [kenml].[Booklet_FindReportsByIdAndDeletedAtIsNull] @ReportsID = :id", nativeQuery = true)
    Reports findReportsByIdAndDeletedAtIsNull(Integer id);


    @Query(value = "exec [kenml].[Booklet_UpdateReports] @ID = :id, @Name = :name, @DaysOfPublications = :timeOfPublication, " +
            "@AutoSending = :autoSending, @Generate_on_Monday = :generateInMonday, " +
            "@Generate_on_Tuesday = :generateInTuesday, @Generate_on_Wednesday = :generateInWednesday, " +
            "@Generate_on_Thursday = :generateInThursday, @Generate_on_Friday = :generateInFriday, " +
            "@Generate_on_Saturday = :generateInSaturday, @Generate_on_Sunday = :generateInSunday, " +
            "@IconPath = :iconPath, @SendAfterTime = :sendAfterTime, @Publicate = :publicate, " +
            "@LastUpdatedBy = :updatedBy, @LastUpdatedOn = :updatedAt", nativeQuery = true)
    Reports update(Integer id, String name, String timeOfPublication, Boolean autoSending,
                Boolean generateInMonday, Boolean generateInTuesday, Boolean generateInWednesday,
                Boolean generateInThursday, Boolean generateInFriday, Boolean generateInSaturday,
                Boolean generateInSunday, String iconPath, Long sendAfterTime, Boolean publicate,
                Integer updatedBy, Date updatedAt);

}
