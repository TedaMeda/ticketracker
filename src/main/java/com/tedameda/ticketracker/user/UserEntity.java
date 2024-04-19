package com.tedameda.ticketracker.user;

import com.tedameda.ticketracker.department.DepartmentEntity;
import com.tedameda.ticketracker.ticket.TicketEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * User Entity mainly have Business and IT role
 *
 * @author TedaMeda
 * @since 2/6/2024
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(name = "email_name", columnNames = {"email"})
})
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "user_name", nullable = false)
    private String name;
    @Column(name = "user_email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "country_code")
    private String countryCode;
    @Column(name = "mobile_number", length = 10)
    private String mobileNumber;
    @Column(name = "user_location", nullable = false)
    private String location;
    @Column(name = "token", nullable = true)
    private String token;
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private DepartmentEntity department;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "createdBy")
    private Set<TicketEntity> ticket;
    @OneToMany(mappedBy = "assignToUser")
    private Set<TicketEntity> assignedTickets;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
