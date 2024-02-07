package com.tedameda.ticketracker.department;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @author TedaMeda
 * @since 2/7/2024
 */
@Entity
@Data
@Table(name = "department")
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
}
