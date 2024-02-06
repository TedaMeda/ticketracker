package com.tedameda.ticketracker.common.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author TedaMeda
 * @since 2/6/2024
 */
@Data
@Builder
public class ErrorResponseDTO {
    private String message;
}
