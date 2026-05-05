package io.github.lighting.dto.request;

import jakarta.validation.constraints.NotNull;

public record SwitchRequest(
        @NotNull Boolean switchOn
) {}
