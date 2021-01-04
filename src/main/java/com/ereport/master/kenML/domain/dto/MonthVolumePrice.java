package com.ereport.master.kenML.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;

import static com.ereport.master.kenML.util.StringUtil.formatNumber;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonthVolumePrice {
    private String monthIndex;
    private Float volume;
    private Float price;

    public String getVolumeStr() throws ParseException {
        if(volume >= 1){
            return String.valueOf(Math.round(volume));
        }else {
            return formatNumber(volume);
        }
    }

    public String getPriceStr() throws ParseException {
        if(price >= 1){
            return String.valueOf(Math.round(price));
        }else {
            return formatNumber(price);
        }
    }
}
