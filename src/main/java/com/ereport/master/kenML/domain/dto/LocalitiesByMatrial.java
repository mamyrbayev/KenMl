package com.ereport.master.kenML.domain.dto;

import com.ereport.master.kenML.domain.Localities;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.util.List;

import static com.ereport.master.kenML.util.StringUtil.*;

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
            if (overallVolume >= 1000000){
                return formatNumberMillion(overallVolume);
            }else {
                return formatNumberSpaces(overallVolume);
            }
        }else {
            return formatNumber(overallVolume);
        }
    }

    public String getOverallPriceStr() throws ParseException {
        if(overallPrice >= 1){
            if (overallPrice >= 1000000){
                return formatNumberMillion(overallPrice);
            }else {
                return formatNumberSpaces(overallPrice);
            }
        }else {
            return formatNumber(overallPrice);
        }
    }
}
