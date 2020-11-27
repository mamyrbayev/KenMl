package com.ereport.master.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="category")
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_category",
        initialValue = 1,
        allocationSize = 1
)
public class Category extends AuditModel{
    @Column(name = "CategoryName")
    private String CategoryName;
    @Column(name = "description")
    private String description;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
//    private List<Contractor> contractors;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "report_category",
            joinColumns = @JoinColumn(name = "report_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Report> report;
}
