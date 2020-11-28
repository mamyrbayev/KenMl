package com.ereport.master.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Component
@Table(name="report")
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_report",
        initialValue = 1,
        allocationSize = 1
)
public class Report extends AuditModel {
    @Column( name = "name")
    private String name;

    @Column(name = "time_of_publication")
    private String timeOfPublication;

    @Column(name = "send_after_time")
    private String sendAfterTime;

    private boolean generateInMonday = false;
    private boolean generateInTuesday = false;
    private boolean generateInWednesday = false;
    private boolean generateInThursday = false;
    private boolean generateInFriday = false;
    private boolean generateInSunday = false;
    private boolean generateInSaturday = false;

    @Column(name= "auto_sending")
    private boolean autoSending = false;


    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "report_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "report_id"))
    private List<Category> category;
}
