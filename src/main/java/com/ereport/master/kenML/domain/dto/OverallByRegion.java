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
    private int completed;
    private int underConstruction;
    private int overall;
}
