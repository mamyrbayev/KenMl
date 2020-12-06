package com.ereport.master.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpirationTimeResponse {
    private int day;
    private int hour;
    private int minute;
    private int second;
}
