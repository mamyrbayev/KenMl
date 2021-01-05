package com.ereport.master.kenML.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import static com.ereport.master.kenML.util.StringUtil.formatNumber;
import static com.ereport.master.kenML.util.StringUtil.formatNumberMillion;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ObjectInYearDto {
    private String year;
    private Float overallVolume;
    private Float overallPrice;
    private List<MonthVolumePrice> monthVolumePrices;

    public List<Float> getMonthVolumePriceArray(){
        return this.monthVolumePrices.stream().map(e -> e.getVolume()).collect(Collectors.toList());
    }

    public String getOverallVolumeStr() throws ParseException {
        if(overallVolume >= 1){
            if (overallVolume >= 1000000){
                return formatNumberMillion(overallVolume);
            }else {
                return String.valueOf(Math.round(overallVolume));
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
                return String.valueOf(Math.round(overallPrice));
            }
        }else {
            return formatNumber(overallPrice);
        }
    }
}
