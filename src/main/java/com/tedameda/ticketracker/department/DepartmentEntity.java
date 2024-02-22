package com.tedameda.ticketracker.department;

import com.tedameda.ticketracker.ticket.TicketEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Set;

/**
 * @author TedaMeda
 * @since 2/7/2024
 */
@Entity
@Data
@Table(name = "department")
@NoArgsConstructor
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NonNull
    private String name;
    @OneToMany(mappedBy = "department")
    private Set<TicketEntity> tickets;
}
