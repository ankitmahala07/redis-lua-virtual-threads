package com.ankitmahala07.virtualthreadredis.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateEventRequest {

    @NotBlank
    public String id;

    @NotBlank
    public String payload;

    @NotNull
    public Long executeAtMillis;
}