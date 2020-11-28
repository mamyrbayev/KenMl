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
    @Column( name = "publication_date")
    private Date publicationDate;
    @Column(name = "sending_date")
    private Date sendingDate;
    @Column(name = "status")
    private Status status;
    @Column(name= "auto_sending")
    private boolean autoSending;
    @ManyToOne
    private Report report;

}
