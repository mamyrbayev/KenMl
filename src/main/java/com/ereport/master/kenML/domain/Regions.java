package com.ereport.master.kenML.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Regions {
    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "Name")
    private String name;
}
