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
    private Long sendAfterTime;

    @Column(name = "generate_in_monday")
    private boolean generateInMonday = false;

    @Column(name = "generate_in_tuesday")
    private boolean generateInTuesday = false;

    @Column(name = "generate_in_wednesday")
    private boolean generateInWednesday = false;

    @Column(name = "generate_in_thursday")
    private boolean generateInThursday = false;

    @Column(name = "generate_in_friday")
    private boolean generateInFriday = false;

    @Column(name = "generate_in_saturday")
    private boolean generateInSaturday = false;

    @Column(name = "generate_in_sunday")
    private boolean generateInSunday = false;

    @Column(name= "auto_sending")
    private boolean autoSending = false;


    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "report_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "report_id"))
    private List<Category> category;
}
