package io.github.lighting.dto.request;

import io.github.lighting.enums.Mode;
import jakarta.validation.constraints.NotNull;

public record ModeRequest(
        @NotNull Mode mode
) {}
