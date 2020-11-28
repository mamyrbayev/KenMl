package com.ereport.master.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Component
@Table(name="report")
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_report",
        initialValue = 1,
        allocationSize = 1
)
public class Report extends AuditModel {
    @Column( name = "name")
    private String name;

    @Column(name = "days_of_publications")
    private String daysOfPublications;


    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "report_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "report_id"))
    private List<Category> category;
}
