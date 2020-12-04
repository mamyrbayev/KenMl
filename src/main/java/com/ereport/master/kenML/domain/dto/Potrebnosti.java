package com.ereport.master.kenML.domain.dto;

import com.ereport.master.domain.MaterialList;
import com.ereport.master.kenML.domain.Material;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Potrebnosti {
    private Material material;
    private List<LocalitiesByMatrial> localitiesByMatrials;
}
