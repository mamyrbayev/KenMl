package com.ereport.master.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@Entity
@Table(name="material_list")
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_material_list",
        initialValue = 1,
        allocationSize = 1
)
public class MaterialList extends AuditModel{
    @Column(name = "material_link")
    private Long mtLink;

    @Column(name = "material_owner")
    private Long mtOwner;

    @Column(name = "material_code")
    private String myCode;

    @Column(name = "material_name")
    private String mtName;

    @Column(name = "material_measure")
    private String mtMeasure;

    @ManyToOne
    private Report report;
}
