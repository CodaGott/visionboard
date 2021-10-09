package com.visionboard.web.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Data
@NotBlank
public class ApiResponse {

    private boolean success;
    private String message;
}
