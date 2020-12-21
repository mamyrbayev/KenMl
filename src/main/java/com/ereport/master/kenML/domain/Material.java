package com.ereport.master.kenML.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Material {

    @Column(name = "MT_LINK")
    private Long mtLink;

    @Column(name = "MT_OWNER")
    private Long mtOwner;

    @Id
    @Column(name = "MT_CODE")
    private String mtCode;

    @Column(name = "MT_NAME")
    private String mtName;

    @Column(name = "MT_MEASURE")
    private String mtMeasure;
}
