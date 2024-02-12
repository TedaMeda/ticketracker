package com.tedameda.ticketracker.user.dto;

import lombok.Data;
import lombok.NonNull;

/**
 * @author TedaMeda
 * @since 2/12/2024
 */
@Data
public class UpdatePasswordRequest {
    @NonNull
    private String email;
    @NonNull
    private String curPassword;
    @NonNull
    private String newPassword;
}
