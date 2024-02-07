package com.tedameda.ticketracker.ticket;

import com.tedameda.ticketracker.user.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

/**
 * @author TedaMeda
 * @since 2/7/2024
 */
@Data
@Entity
@Table(name = "ticket")
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @CreatedDate
    @Column(name = "created_date")
    private Date createdDate;
    @LastModifiedDate
    @Column(name = "last_modify_date")
    private Date lastModifyDate;
    @ManyToOne
    @JoinColumn(name = "assign_to", nullable = true)
    private UserEntity assignToUser;
    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private UserEntity createdBy;
    private String title;
    private String description;

    //TODO think about alternative ways to write ENUM or convert it into table
    @Column(name = "request_type")
    private RequestType requestType;
    private TicketStatus status;
}
