package com.shadowSin.task_tracker.dto;

import jakarta.validation.constraints.NotBlank;


public record TaskRequest(@NotBlank String title, @NotBlank String description, boolean completed) {



    
}
