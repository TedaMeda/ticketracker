package com.tedameda.ticketracker.user.dto;

import lombok.Data;

import java.util.Set;

/**
 * @author TedaMeda
 * @since 2/6/2024
 */
@Data
public class UpdateRolesRequest {
    private Set<String> roles;
}
