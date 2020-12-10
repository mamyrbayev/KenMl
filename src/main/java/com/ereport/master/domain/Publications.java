package com.ereport.master.domain;

import com.ereport.master.domain.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column( name = "publication_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone="Asia/Almaty")
    private Date publicationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name= "auto_sending")
    private boolean autoSending = false;

    @Column(name= "icon_path")
    private String iconPath;

    @Column(name= "file_path")
    private String filePath;

    @ManyToOne
    @JsonIgnore
    private Report report;

}
