package com.tedameda.ticketracker.ticket;

import com.tedameda.ticketracker.department.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author TedaMeda
 * @since 2/7/2024
 */
public interface TicketRepository extends JpaRepository<TicketEntity,Long> {
    List<TicketEntity> findAllByDepartment(DepartmentEntity department);
}
