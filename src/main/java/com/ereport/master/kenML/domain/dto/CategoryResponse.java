package com.ereport.master.kenML.domain.dto;

import com.ereport.master.domain.Contractor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
    private String categoryName;
    private String description;
    private List<Contractor> contractors;
}
