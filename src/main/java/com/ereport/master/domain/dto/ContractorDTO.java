package com.ereport.master.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractorDTO {
    private String contractorName;
    private String bin;
    private String eMail;
    private String phoneNumber;
}
