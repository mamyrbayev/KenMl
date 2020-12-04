package com.ereport.master.kenML.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Resources {
    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "FileSectionID")
    private Integer fileSectionId;

    @Column(name = "ResourceID")
    private String resourceId;

    @Column(name = "ResourceName")
    private String resourceName;

    @Column(name = "ResourceType")
    private Integer resourceType;

    @Column(name = "ResourceVolume")
    private Float resourceVolume;

    @Column(name = "CodeSNB")
    private String codeSNB;

    @Column(name = "CodeLocal")
    private String codeLocal;

    @Column(name = "Price")
    private Float price;

    @Column(name = "Norma")
    private Float norma;

    @Column(name = "Measurer")
    private String measurer;

    @Column(name = "Delivery")
    private String delivery;

    @Column(name = "LastUpdatedOn")
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

    @Column(name = "Status")
    private String status;

}
