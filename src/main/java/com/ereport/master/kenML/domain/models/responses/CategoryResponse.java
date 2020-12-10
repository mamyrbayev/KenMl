package com.ereport.master.kenML.domain.models.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
    private long ID;
    private String Name;
    private String Description;
    private Date CreatedOn;
    private Date LastUpdatedOn;
    private Integer CreatedBy;
    private Integer LastUpdatedBy;

}
