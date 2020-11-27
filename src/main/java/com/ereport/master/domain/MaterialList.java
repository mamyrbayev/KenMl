package com.ereport.master.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@Entity
@Component
@Table(name="materialList")
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_materialList",
        initialValue = 1,
        allocationSize = 1
)
public class MaterialList extends AuditModel{
    @Column(name = "materialName")
    private String materialName;
    @ManyToOne
    private Report report;
}
