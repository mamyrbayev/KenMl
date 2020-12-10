package com.ereport.master.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
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
    @Column(name = "category_name")
    private String categoryName;
    @Column(name = "description")
    private String description;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "report_category",
            joinColumns = @JoinColumn(name = "report_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Report> report;
}
