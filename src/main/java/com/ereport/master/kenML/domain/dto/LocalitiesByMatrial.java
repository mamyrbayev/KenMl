package com.ereport.master.kenML.domain.dto;

import com.ereport.master.kenML.domain.Companies;
import com.ereport.master.kenML.domain.Localities;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocalitiesByMatrial {
    private Localities localities;
    private Float overallVolume;
    private Float overallPrice;
    private List<Companies> companiesList;
}
