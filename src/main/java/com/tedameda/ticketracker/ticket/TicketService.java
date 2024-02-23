package com.tedameda.ticketracker.ticket;

import com.tedameda.ticketracker.department.DepartmentEntity;
import com.tedameda.ticketracker.department.DepartmentService;
import com.tedameda.ticketracker.ticket.dto.CreateTicketRequest;
import com.tedameda.ticketracker.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    public TicketService(TicketRepository ticketRepository, DepartmentService departmentService, ModelMapper modelMapper, UserService userService) {
        this.ticketRepository = ticketRepository;
        this.departmentService = departmentService;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    public TicketEntity createTicket(Long userId, CreateTicketRequest request) {
        var ticket = modelMapper.map(request, TicketEntity.class);
        DepartmentEntity department = departmentService.getDepartment(request.getDepartment());
        ticket.setCreatedBy(userService.getUser(userId));
        ticket.setDepartment(department);
        ticket.setStatus(TicketStatus.NOT_ASSIGNED);
        return ticketRepository.save(ticket);
    }
    public List<TicketEntity> getAllTickets(int pageNo, int pageSize){
        PageRequest pageRequest = PageRequest.of(pageNo,pageSize, Sort.by("createdDate").descending().and(Sort.by("lastModifyDate").descending()));
        Page<TicketEntity> tickets = ticketRepository.findAll(pageRequest);
        return tickets.getContent();
    }
    public List<TicketEntity> getTicketByDepartment(String departmentName, int pageNo, int pageSize) {
        var department = departmentService.getDepartment(departmentName);
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        Page<TicketEntity> tickets = ticketRepository.findAllByDepartment(department,pageRequest);
        return tickets.getContent();
    }
}
