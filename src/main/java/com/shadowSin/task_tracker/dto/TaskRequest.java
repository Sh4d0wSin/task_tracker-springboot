package com.shadowSin.task_tracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TaskRequest(@NotBlank String title, @NotBlank String description, boolean completed) {



    
}
