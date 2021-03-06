package com.ereport.master.kenML.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static com.ereport.master.kenML.util.StringUtil.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompaniesDto {
    private Integer id;
    private String bin;
    private String title;
    private String directorName;
    private String directorPhone;
    private String emailAddress;
    private String physicalAddress;
    private Integer categoryID;

    private Float overallVolume;
    private Float overallPrice;

    private List<ObjectsDto> objectsDto;


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
