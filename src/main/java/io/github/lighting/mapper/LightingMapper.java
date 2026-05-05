package io.github.lighting.mapper;

import io.github.lighting.domain.Lighting;
import io.github.lighting.dto.response.LightingResponse;
import org.springframework.stereotype.Component;

@Component
public class LightingMapper {

    public LightingResponse toResponse(Lighting lighting) {
        return new LightingResponse(
                lighting.getId(),
                lighting.getMode(),
                lighting.getLuminosity(),
                lighting.getLedOn(),
                lighting.getSwitchOn(),
                lighting.getThreshold()
        );
    }
}
