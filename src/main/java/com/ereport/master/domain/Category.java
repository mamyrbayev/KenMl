package com.ereport.master.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
@Table(name="Categories")
@NoArgsConstructor
@AllArgsConstructor
public class Category{
    @Id
    @GeneratedValue(generator = "seq")
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "Name")
    private String categoryName;

    @Column(name = "Description")
    private String description;

    @Column(name = "CreatedBy")
    private Integer createdBy;

    @Column(name = "CreatedOn")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Date createdAt;

    @Column(name = "LastUpdatedOn")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Date updatedAt;

    @Column(name = "LastUpdatedBy")
    private Integer updatedBy;

//    @ManyToMany
//    @JsonIgnore
//    @JoinTable(name = "report_category",
//            joinColumns = @JoinColumn(name = "report_id"),
//            inverseJoinColumns = @JoinColumn(name = "category_id"))
//    private List<Report> report;
}
