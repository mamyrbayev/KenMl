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
@Table(name="Categories")
public class Categories {

    @Id
    @Column( name = "ID")
    private Integer id;

    @Column( name = "Name")
    private String name;

    @Column( name = "Description")
    private String description;

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
