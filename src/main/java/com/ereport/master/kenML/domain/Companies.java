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

    @Column(name = "updated_at")
    private Date lastUpdatedOn = new Date();

    @Column(name = "bin")
    private String bin;

    @Column(name = "title")
    private String title;

    @Column(name = "director_name")
    private String directorName;

    @Column(name = "director_phone")
    private String directorPhone;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "physical_address")
    private String physicalAddress;

    @Column(name = "CategoryID")
    private Integer categoryID;

    @Column(name = "contact_name")
    private String contactName;

    @Column(name = "contact_phone")
    private String contactPhone;

}
