package com.tedameda.ticketracker.user.dto;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * @author TedaMeda
 * @since 2/6/2024
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    @NonNull
    private String name;
    @NonNull
    private String email;
    @Nullable
    private String countryCode;
    @Nullable
    private String mobileNumber;
    @NonNull
    private String location;
    @NonNull
    private String department;
}