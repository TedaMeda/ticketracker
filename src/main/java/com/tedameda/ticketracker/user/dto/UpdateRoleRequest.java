package com.tedameda.ticketracker.user.dto;

import com.tedameda.ticketracker.user.Role;
import lombok.Data;
import lombok.NonNull;

/**
 * @author TedaMeda
 * @since 2/6/2024
 */
@Data
public class UpdateRoleRequest {
    @NonNull
    private String email;
    @NonNull
    private Role role;
}
