package com.ereport.master.kenML.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ObjectWorkDate {
    private Integer id;

    private String objectName;

    private Integer companyId;

    private Integer localityId;

    private Date lastUpdatedOn;

    private Date endDate;
}
