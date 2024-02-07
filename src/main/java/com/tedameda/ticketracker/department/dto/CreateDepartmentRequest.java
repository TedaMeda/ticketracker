package com.tedameda.ticketracker.department.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author TedaMeda
 * @since 2/7/2024
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateDepartmentRequest {
    private String name;
}
