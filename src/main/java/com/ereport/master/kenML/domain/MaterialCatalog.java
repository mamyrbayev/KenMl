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
public class MaterialCatalog {
    @Id
    private Long id;

    @Column(name = "MC_LEVEL")
    private Integer mcLevel;

    @Column(name = "MC_OWNER")
    private Long mcOwner;

    @Column(name = "MC_CODE")
    private Long mcCode;

    @Column(name = "MC_NAME")
    private String mcName;

    @Column(name = "MC_COUNT")
    private Long mcCount;
}
