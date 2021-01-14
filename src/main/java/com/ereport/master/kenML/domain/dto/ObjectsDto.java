package com.ereport.master.kenML.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ObjectsDto {
    private Integer id;
    private String objectName;
    private Integer companyId;
    private Integer localityId;
    private Date lastUpdatedOn;
    private List<ObjectInYearDto> objectInYearDtos;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date startDate;

    public String getStartDateStr() {
        if (Objects.isNull(startDate)) {
            return "";
        }
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(startDate);
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date endDate;

    public String getEndDateStr() {
        if (Objects.isNull(endDate)) {
            return "";
        }
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(endDate);
    }

}
