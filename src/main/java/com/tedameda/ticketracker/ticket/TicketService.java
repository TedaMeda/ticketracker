package com.tedameda.ticketracker.ticket;

import com.tedameda.ticketracker.department.DepartmentService;
import com.tedameda.ticketracker.ticket.dto.CreateTicketRequest;
import com.tedameda.ticketracker.user.UserEntity;
import com.tedameda.ticketracker.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author TedaMeda
 * @since 2/7/2024
 */

@Service
public class TicketService {
    TicketRepository ticketRepository;
    DepartmentService departmentService;
    ModelMapper modelMapper;
    UserService userService;
    public TicketService(TicketRepository ticketRepository, ModelMapper modelMapper) {
        this.ticketRepository = ticketRepository;
        this.modelMapper = modelMapper;
    }
    public TicketEntity createTicket(UserEntity user, CreateTicketRequest request){
        var ticket = modelMapper.map(request, TicketEntity.class);
        ticket.setCreatedBy(user);
        return ticketRepository.save(ticket);
    }
    public List<TicketEntity> getTicketByDepartment(String departmentName){
        var department = departmentService.getDepartment(departmentName);
        List<TicketEntity> tickets = ticketRepository.findAllByDepartment(department);
        return tickets;
    }
}
