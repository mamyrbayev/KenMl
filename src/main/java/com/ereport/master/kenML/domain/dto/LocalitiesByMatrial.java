package com.ereport.master.kenML.domain.dto;

import com.ereport.master.kenML.domain.Companies;
import com.ereport.master.kenML.domain.Localities;
import com.google.common.base.CharMatcher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

import static com.ereport.master.kenML.util.StringUtil.formatNumber;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocalitiesByMatrial {
    private Localities localities;
    private Float overallVolume;
    private Float overallPrice;
    private List<CompaniesDto> companiesList;

    public String getOverallVolumeStr() throws ParseException {
        if(overallVolume >= 1){
            return String.valueOf(Math.round(overallVolume));
        }else {
            return formatNumber(overallVolume);
        }
    }

    public String getOverallPriceStr() throws ParseException {
        if(overallPrice >= 1){
            return String.valueOf(Math.round(overallPrice));
        }else {
            return formatNumber(overallPrice);
        }
    }
}
