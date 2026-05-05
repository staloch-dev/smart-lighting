package io.github.lighting.dto.response;

import io.github.lighting.enums.Mode;

public record LightingResponse(
        Long id,
        Mode mode,
        Integer luminosity,
        Boolean ledOn,
        Boolean switchOn,
        Integer threshold
) {}
