package com.ereport.master.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@Entity
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
    @Column(name = "contractor_name")
    private String contractorName;
    @Column(name = "bin")
    private int bin;
    @Column(name = "e_mail")
    private String eMail;
    @Column(name = "phone_number")
    private int phoneNumber;
    @ManyToOne
    private Category category;
}

