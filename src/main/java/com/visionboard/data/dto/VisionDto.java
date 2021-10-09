package com.visionboard.data.dto;

import com.visionboard.data.model.Category;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
@Data
public class VisionDto {
    @NotBlank(message = "Title can't be null")
    @Size(min = 5, max = 30)
    private String title;
    @NotBlank(message = "Vision can't be null")
    @Size(min = 3)
    private String vision;
    private LocalDateTime createdOn;
    private LocalDateTime lastUpdated;
    private Category category;
}
