package com.ereport.master.domain.dto;

import com.ereport.master.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportDTO {
    private Long id;
    private String name;

    private String timeOfPublication;

    private Long sendAfterTime;

    private boolean generateInMonday = false;

    private boolean generateInTuesday = false;

    private boolean generateInWednesday = false;

    private boolean generateInThursday = false;

    private boolean generateInFriday = false;

    private boolean generateInSaturday = false;

    private boolean generateInSunday = false;

    private boolean autoSending = false;

    private List<Category> category;

    private String status;
    private Integer numOfReports;
}
