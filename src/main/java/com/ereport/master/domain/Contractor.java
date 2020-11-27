package com.ereport.master.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@Entity
@Component
@Table(name="contractor")
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_contractor",
        initialValue = 1,
        allocationSize = 1
)
public class Contractor extends AuditModel{
    @Column(name = "contr_name")
    private String contrName;
    @Column(name = "bin")
    private int bin;
    @Column(name = "eMail")
    private String eMail;
    @Column(name = "phoneNum")
    private int phoneNum;
    @ManyToOne
    private Category category;
}

