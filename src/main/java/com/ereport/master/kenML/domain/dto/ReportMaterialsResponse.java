package com.ereport.master.kenML.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportMaterialsResponse {
    private Integer id;
    private Integer reportId;
    private Long mtLink;
    private Long mtOwner;
    private String mtCode;
    private String mtName;
    private String mtMeasure;
}
