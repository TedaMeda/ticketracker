package com.tedameda.ticketracker.user.dto;

import com.tedameda.ticketracker.user.UserPermission;
import lombok.Data;
import lombok.NonNull;

/**
 * @author TedaMeda
 * @since 2/6/2024
 */
@Data
public class UpdatePermissionRequest {
    @NonNull
    private String email;
    @NonNull
    private UserPermission permission;
}
