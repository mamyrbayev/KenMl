package com.ereport.master.kenML.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ObjectInYearDto {
    private String year;
    private Float overallVolume;
    private Float overallPrice;
    private List<MonthVolumePrice> monthVolumePrices;
}
