package com.tedameda.ticketracker.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author TedaMeda
 * @since 2/6/2024
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private String countryCode;
    private String mobileNumber;
    private String location;
    private String permission;
    private String token;
    private String role;
}
