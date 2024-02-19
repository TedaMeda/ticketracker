package com.tedameda.ticketracker.ticket.dto;

import com.tedameda.ticketracker.ticket.TicketEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * @author TedaMeda
 * @since 2/7/2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTicketRequest {
    @NonNull
    private String title;
    @NonNull
    private String description;
    @NonNull
    private TicketEntity.RequestType requestType;
}
