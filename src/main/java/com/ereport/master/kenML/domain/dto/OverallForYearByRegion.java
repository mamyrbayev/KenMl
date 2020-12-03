package com.ereport.master.kenML.domain.dto;

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
public class OverallForYearByRegion {
    private String year;
    private List<OverallByRegion> overallByRegions;
}
