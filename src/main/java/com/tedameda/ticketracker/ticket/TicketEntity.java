package com.tedameda.ticketracker.ticket;

import com.tedameda.ticketracker.department.DepartmentEntity;
import com.tedameda.ticketracker.user.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;
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
    @NonNull
    private String title;
    @NonNull
    private String description;
    @Column(name = "request_type")
    private RequestType requestType;
    @NonNull
    private TicketStatus status;
    @OneToMany
    @JoinColumn(name = "department_name", nullable = false)
    private DepartmentEntity department;

    public enum RequestType {
        SOFTWARE_REQUEST,
        HARDWARE_REQUEST,
        CLOUD_SERVICE_REQUEST,
        GITLAB_RESOURCE_REQUEST,
        SEATING_CHANGE_REQUEST,
        EMPLOYEE_VERIFICATION_REQUEST,
        CREATE_CREDENTIAL_REQUEST;
    }

    public enum TicketStatus {
        NOT_ASSIGNED,
        ACTIVE,
        CLOSED;
    }
}
