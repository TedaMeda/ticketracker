package com.tedameda.ticketracker.user.dto;

import lombok.*;

/**
 * @author TedaMeda
 * @since 2/10/2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequest {
    @NonNull
    private String email;
    @NonNull
    private String password;
}
