package com.ereport.master.kenML.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Companies {
    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "LastUpdatedOn")
    private Date lastUpdatedOn;

    @Column(name = "BIN")
    private String bin;

    @Column(name = "Title")
    private String title;

    @Column(name = "DirectorName")
    private String directorName;

    @Column(name = "DirectorPhone")
    private String directorPhone;

    @Column(name = "EmailAddress")
    private String emailAddress;

    @Column(name = "PhysicalAddress")
    private String physicalAddress;

    @Column(name = "CategoryID")
    private Integer categoryID;

}
