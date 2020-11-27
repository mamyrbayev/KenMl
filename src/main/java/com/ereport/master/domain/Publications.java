package com.ereport.master.domain;

import com.ereport.master.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Component
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
    @Column( name = "publicationDate")
    private Date publicationDate;
    @Column(name = "sendingDate")
    private Date sendingDate;
    @Column(name = "status")
    private Status status;
    @Column(name= "autoSending")
    private boolean autoSending;
    @ManyToOne
    private Report report;

}
