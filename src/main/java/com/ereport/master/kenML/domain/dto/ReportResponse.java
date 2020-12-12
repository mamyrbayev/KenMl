package com.ereport.master.kenML.domain.dto;

import com.ereport.master.kenML.domain.Categories;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportResponse {
    private Integer id;

    private String name;

    private String timeOfPublication;

    private boolean autoSending = false;

    private boolean generateInMonday = false;

    private boolean generateInTuesday = false;

    private boolean generateInWednesday = false;

    private boolean generateInThursday = false;

    private boolean generateInFriday = false;

    private boolean generateInSaturday = false;

    private boolean generateInSunday = false;

    private boolean publicate = false;

    private String iconPath;

    private Long sendAfterTime;

    private List<Categories> category;

    private String status;
    private Integer numOfReports;

}
