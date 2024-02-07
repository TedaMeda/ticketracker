package com.tedameda.ticketracker.ticket;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author TedaMeda
 * @since 2/7/2024
 */
public interface TicketRepository extends JpaRepository<TicketEntity,Long> {
}
