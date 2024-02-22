package com.tedameda.ticketracker.ticket;

import com.tedameda.ticketracker.ticket.dto.CreateTicketRequest;
import com.tedameda.ticketracker.user.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * @author TedaMeda
 * @since 2/7/2024
 */

//TODO come up with API definitions
@RestController
@RequestMapping("/tickets")
public class TicketController {
    TicketService ticketService;
    ModelMapper modelMapper;

    public TicketController(TicketService ticketService, ModelMapper modelMapper) {
        this.ticketService = ticketService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("")
    public ResponseEntity<TicketEntity> createTicket(@RequestBody CreateTicketRequest request, @AuthenticationPrincipal UserEntity user){
        var ticket = ticketService.createTicket(user.getId(), request);
        URI uri = URI.create("/tickets/"+ticket.getId());
        return ResponseEntity.created(uri).body(ticket);
    }
}
