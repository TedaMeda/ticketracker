package com.tedameda.ticketracker.ticket;

import com.tedameda.ticketracker.ticket.dto.CreateTicketRequest;
import com.tedameda.ticketracker.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * @author TedaMeda
 * @since 2/7/2024
 */
@Service
public class TicketService {
    TicketRepository ticketRepository;
    ModelMapper modelMapper;
    UserService userService;
    public TicketService(TicketRepository ticketRepository, ModelMapper modelMapper) {
        this.ticketRepository = ticketRepository;
        this.modelMapper = modelMapper;
    }

    public TicketEntity createTicket(Long id, CreateTicketRequest request){
        var ticket = modelMapper.map(request, TicketEntity.class);
        var createdByUser = userService.getUser(id);
        ticket.setCreatedBy(createdByUser);
        return ticket;
    }
}
