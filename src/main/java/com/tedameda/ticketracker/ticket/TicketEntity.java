package com.tedameda.ticketracker.ticket;

import com.tedameda.ticketracker.department.DepartmentEntity;
import com.tedameda.ticketracker.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

/**
 * @author TedaMeda
 * @since 2/7/2024
 */
@Data
@Entity
@Table(name = "ticket")
@NoArgsConstructor
@AllArgsConstructor
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @CreationTimestamp
    @Column(name = "created_date")
    private Date createdDate;
    @UpdateTimestamp
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
    @Enumerated(EnumType.STRING)
    @Column(name = "request_type", nullable = false)
    private RequestType requestType;
    @NonNull
    @Enumerated(EnumType.STRING)
    private TicketStatus status;
    @ManyToOne
    @JoinColumn(name = "department_name", nullable = false)
    private DepartmentEntity department;
}
