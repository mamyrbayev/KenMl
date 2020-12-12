package com.ereport.master.kenML.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Reports")
public class Reports {

    @Id
    @Column( name = "ID")
    private Integer id;
    
    @Column( name = "name")
    private String name;

    @Column(name = "time_of_publication")
    private String timeOfPublication;

    @Column(name= "sending_automatically")
    private boolean autoSending = false;

    @Column(name = "Generate_on_Monday")
    private boolean generateInMonday = false;

    @Column(name = "Generate_on_Tuesday")
    private boolean generateInTuesday = false;

    @Column(name = "Generate_on_Wednesday")
    private boolean generateInWednesday = false;

    @Column(name = "Generate_on_Thursday")
    private boolean generateInThursday = false;

    @Column(name = "Generate_on_Friday")
    private boolean generateInFriday = false;

    @Column(name = "Generate_on_Saturday")
    private boolean generateInSaturday = false;

    @Column(name = "Generate_on_Sunday")
    private boolean generateInSunday = false;

    @Column(name= "path_icon")
    private String iconPath;

    @Column(name = "send_after_time")
    private Long sendAfterTime;

    @Column(name = "Publicate")
    private boolean publicate = false;

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
