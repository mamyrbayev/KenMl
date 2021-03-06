package com.ereport.master.kenML.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="ReportCategories")
public class ReportCategories {

    @Id
    @Column( name = "ID")
    private Integer id;

    @Column(name = "ReportID")
    private Integer reportId;

    @Column(name = "CategoryID")
    private Integer categoryId;
}
