package com.ereport.master.domain.dto;

import com.ereport.master.domain.Contractor;
import com.ereport.master.domain.Report;
import com.ereport.master.domain.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicationsResponse  {
    @JsonFormat(shape = JsonFormat.Shape.STRING) //, pattern = "yyyy-MM-dd HH:mm a z"
    private Date createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING) //, pattern = "yyyy-MM-dd HH:mm a z"
    private Date publicationDate;

    private Status status;

    private boolean autoSending = false;

    private ReportDTO report;

    private List<Contractor> receivers;

}
