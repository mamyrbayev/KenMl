package com.ereport.master.kenML.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Year;
import java.util.Date;
import java.util.List;

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

    private Date startDate;
    private Date endDate;

}
