package com.tedameda.ticketracker.department;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author TedaMeda
 * @since 2/7/2024
 */
@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Long> {
    Optional<DepartmentEntity> findByName(String name);
}
