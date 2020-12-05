package com.ereport.master.domain;

import com.ereport.master.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="publication")
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_publication",
        initialValue = 1,
        allocationSize = 1
)
public class Publications extends AuditModel {
    @Column( name = "publication_date")
    private Date publicationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name= "auto_sending")
    private boolean autoSending = false;

    @ManyToOne
    private Report report;

}
