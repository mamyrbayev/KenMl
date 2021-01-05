package com.ereport.master.kenML.domain.dto;

import lombok.*;

import java.text.ParseException;

import static com.ereport.master.kenML.util.StringUtil.formatNumber;
import static com.ereport.master.kenML.util.StringUtil.formatNumberMillion;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OverallVolumeAndPrice {
    private Float volume;
    private Float price;
    private String volumeStr;
    private String priceStr;
    private String measurer;

    public String getVolumeStr() throws ParseException {
        if(volume >= 1){
            if (volume >= 1000000){
                return formatNumberMillion(volume);
            }else {
                return String.valueOf(Math.round(volume));
            }
        }else {
            return formatNumber(volume);
        }
    }

    public String getPriceStr() throws ParseException {
        if(price >= 1){
            if (price >= 1000000){
                return formatNumberMillion(price);
            }else {
                return String.valueOf(Math.round(price));
            }
        }else {
            return formatNumber(price);
        }
    }
}
