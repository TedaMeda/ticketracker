package com.tedameda.ticketracker.ticket;

import com.tedameda.ticketracker.ticket.dto.CreateTicketRequest;
import com.tedameda.ticketracker.user.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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

    @GetMapping("")
    public ResponseEntity<List<TicketEntity>> getAllTickets(
            @RequestParam(required = false) String departmentName,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize
    ){
        List<TicketEntity> tickets;
        if(departmentName==null)
             tickets = ticketService.getAllTickets(pageNo, pageSize);
        else
            tickets = ticketService.getTicketByDepartment(departmentName, pageNo, pageSize);
        return ResponseEntity.ok(tickets);
    }

}
