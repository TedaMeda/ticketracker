package com.tedameda.ticketracker.user;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author TedaMeda
 * @since 2/6/2024
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
