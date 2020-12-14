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
public class Resources {
    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "file_section_id")
    private Integer fileSectionId;

    @Column(name = "resource_id")
    private String resourceId;

    @Column(name = "resource_name")
    private String resourceName;

    @Column(name = "resource_type")
    private Integer resourceType;

    @Column(name = "resource_volume")
    private Float resourceVolume;

    @Column(name = "code_snb")
    private String codeSNB;

    @Column(name = "code_local")
    private String codeLocal;

    @Column(name = "Price")
    private Float price;

    @Column(name = "Norma")
    private Float norma;

    @Column(name = "measurer")
    private String measurer;

    @Column(name = "delivery")
    private String delivery;

    @Column(name = "updated_at")
    private Date lastUpdatedOn;

    @Column(name = "PZ")
    private Float pz;

    @Column(name = "ZP")
    private Float zp;

    @Column(name = "SR")
    private Float sr;

    @Column(name = "ZM")
    private Float zm;

    @Column(name = "EM")
    private Float em;

    @Column(name = "status")
    private String status;

}
