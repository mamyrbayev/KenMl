package com.ereport.master.kenML.domain.dto;

import com.ereport.master.kenML.domain.Companies;
import com.ereport.master.kenML.domain.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicationsResponse {

    private Integer id;

    private Integer reportId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone="Asia/Almaty")
    private Date publicationDate = new Date();

    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone="Asia/Almaty")
    private Date sendingDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private Status status;

    private boolean autoSending = false;

    private String filePath;

    private Integer createdBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone="Asia/Almaty")
    private Date createdAt = new Date();

    private Integer updatedBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone="Asia/Almaty")
    private Date updatedAt = new Date();

    private List<Companies> receivers;
}
