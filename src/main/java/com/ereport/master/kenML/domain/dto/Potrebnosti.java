package com.ereport.master.kenML.domain.dto;

import com.ereport.master.kenML.domain.Material;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Potrebnosti {
    private Material material;
    private List<LocalitiesByMatrial> localitiesByMatrials;
}
