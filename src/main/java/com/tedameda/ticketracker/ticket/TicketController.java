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
            @RequestParam(name = "department",required = false) String departmentName,
            @RequestParam(name = "requestType", required = false) String requestType,
            @RequestParam(name = "status", required = false) String status,
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

    @GetMapping("/my-ticket")
    public ResponseEntity<List<TicketEntity>> getUserAllTicket(@AuthenticationPrincipal UserEntity user){
        var tickets = ticketService.getAllTicketsByUserId(user);
        return ResponseEntity.ok(tickets);
    }

    @PutMapping("/{ticket-id}/assign-ticket")
    public ResponseEntity<TicketEntity> assignTicket(@PathVariable(name = "ticket-id") Long ticketId, Long userId){
        var ticket = ticketService.assignTicketToUser(ticketId,userId);
        return ResponseEntity.ok(ticket);
    }

    @PutMapping("/{ticket-id}/update-status")
    public ResponseEntity<TicketEntity> updateTicketStatus(@PathVariable(name="ticket-id") Long ticketId, TicketStatus ticketStatus){
        var ticket = ticketService.updateStatus(ticketId,ticketStatus);
        return ResponseEntity.ok(ticket);
    }
}
