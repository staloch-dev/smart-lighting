package io.github.lighting.service;

import io.github.lighting.domain.Lighting;
import io.github.lighting.dto.request.ModeRequest;
import io.github.lighting.dto.request.SensorRequest;
import io.github.lighting.dto.request.SwitchRequest;
import io.github.lighting.dto.request.ThresholdRequest;
import io.github.lighting.dto.response.LightingResponse;
import io.github.lighting.enums.Mode;
import io.github.lighting.exception.LightingNotFoundException;
import io.github.lighting.mapper.LightingMapper;
import io.github.lighting.repository.LightingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LightingService {

    private final LightingRepository repository;
    private final LightingMapper mapper;

    private Lighting getState() {
        return repository.findById(1L)
                .orElseThrow(LightingNotFoundException::new);
    }

    public LightingResponse getCurrentState() {
        return mapper.toResponse(getState());
    }

    public LightingResponse updateSensor(SensorRequest request) {
        Lighting lighting = getState();
        lighting.setLuminosity(request.luminosity());

        if (!lighting.getSwitchOn()) {
            lighting.setLedOn(false);
        } else if (lighting.getMode() == Mode.AUTOMATIC) {
            lighting.setLedOn(request.luminosity() < lighting.getThreshold());
        }

        return mapper.toResponse(repository.save(lighting));
    }

    public LightingResponse updateMode(ModeRequest request) {
        Lighting lighting = getState();
        lighting.setMode(request.mode());

        if (request.mode() == Mode.AUTOMATIC) {
            lighting.setLedOn(false);
        }

        return mapper.toResponse(repository.save(lighting));
    }

    public LightingResponse updateThreshold(ThresholdRequest request) {
        Lighting lighting = getState();
        lighting.setThreshold(request.threshold());
        return mapper.toResponse(repository.save(lighting));
    }

    public LightingResponse updateSwitch(SwitchRequest request) {
        Lighting lighting = getState();
        lighting.setSwitchOn(request.switchOn());

        if (!request.switchOn()) {
            lighting.setLedOn(false);
        }

        return mapper.toResponse(repository.save(lighting));
    }

}
