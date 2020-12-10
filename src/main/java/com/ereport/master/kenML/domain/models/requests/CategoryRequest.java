package com.ereport.master.kenML.domain.models.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {
    private String CategoryName;
    private String description;
    private Integer createdBy;
    private Integer updatedBy;
}
