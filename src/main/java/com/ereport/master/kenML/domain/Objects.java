package com.ereport.master.kenML.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Objects {
    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "object_name")
    private String objectName;

    @Column(name = "CompanyID")
    private Integer companyId;

    @Column(name = "LocalityID")
    private Integer localityId;

    @Column(name = "updated_at")
    private Date lastUpdatedOn;
}
