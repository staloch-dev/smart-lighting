package io.github.lighting.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;

public record ThresholdRequest(
        @NotNull @Min(0) Integer threshold
) {}
