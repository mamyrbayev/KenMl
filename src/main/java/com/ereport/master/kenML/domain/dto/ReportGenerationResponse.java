package com.ereport.master.kenML.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportGenerationResponse {
    private List<MaterialDTO> topTen;
    private List<OverallForYear> overallForYears;

}
