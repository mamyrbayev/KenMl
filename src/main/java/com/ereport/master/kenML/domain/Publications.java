package com.ereport.master.kenML.domain;

import com.ereport.master.domain.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Publications")
public class Publications {

    @Id
    @Column( name = "ID")
    private Integer id;

    @Column( name = "ReportID")
    private Integer reportId;

    @Column( name = "PublicationDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone="Asia/Almaty")
    private Date publicationDate = new Date();

    @Column( name = "SendingDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone="Asia/Almaty")
    private Date sendingDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private Status status;

    @Column(name= "AutoSending")
    private boolean autoSending = false;

    @Column(name= "FilePath")
    private String filePath;

    @Column(name = "created_by")
    private Integer createdBy;

    @CreatedDate
    @Column(name = "created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone="Asia/Almaty")
    private Date createdAt = new Date();

    @Column(name = "updated_by")
    private Integer updatedBy;

    @LastModifiedDate
    @Column(name = "updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone="Asia/Almaty")
    private Date updatedAt = new Date();


}


