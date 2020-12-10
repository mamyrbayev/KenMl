package com.ereport.master.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryContractorsRequest {
    private String CategoryName;
    private String description;
    private List<ContractorDTO> contractors;
}
