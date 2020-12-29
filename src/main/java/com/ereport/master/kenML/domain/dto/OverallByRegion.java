package com.ereport.master.kenML.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OverallByRegion {
    private Integer regionId;
    private String regionName;
    private int completed = 0;
    private int underConstruction = 0;
    private int overall = 0;
}
