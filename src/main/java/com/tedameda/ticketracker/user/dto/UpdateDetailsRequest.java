package com.tedameda.ticketracker.user.dto;

import com.tedameda.ticketracker.user.Role;
import jakarta.annotation.Nullable;
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
}
