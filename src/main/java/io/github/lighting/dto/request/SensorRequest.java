package io.github.lighting.dto.request;

import jakarta.validation.constraints.NotNull;

public record SensorRequest(
        @NotNull Integer luminosity
) {}
