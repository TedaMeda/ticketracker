package com.tedameda.ticketracker.user.dto;

import com.tedameda.ticketracker.department.DepartmentEntity;
import com.tedameda.ticketracker.user.UserPermission;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

/**
 * @author TedaMeda
 * @since 2/12/2024
 */
@Data
public class UpdateDetailsRequest {
    @Nullable
    private String countryCode;
    @Nullable
    private String mobileNumber;
    @Nullable
    private String location;
    @Nullable
    private String department;
    @Nullable
    private UserPermission permission;
}
