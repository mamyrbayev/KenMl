package com.ereport.master.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@Entity
@Component
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
    @Column(name = "material_name")
    private String materialName;

    @Column(name = "material_link")
    private String materialLink;

    @Column(name = "material_owner")
    private String materialOwner;

    @Column(name = "material_measure")
    private String materialMeasure;

    @ManyToOne
    private Report report;
}
