package io.github.lighting.controller;

import io.github.lighting.dto.request.ModeRequest;
import io.github.lighting.dto.request.SensorRequest;
import io.github.lighting.dto.request.SwitchRequest;
import io.github.lighting.dto.request.ThresholdRequest;
import io.github.lighting.dto.response.LightingResponse;
import io.github.lighting.service.LightingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class LightingController {

    private final LightingService service;

    @GetMapping("/estado")
    public ResponseEntity<LightingResponse> getState() {
        return ResponseEntity.ok(service.getCurrentState());
    }

    @PostMapping("/sensor")
    public ResponseEntity<LightingResponse> updateSensor(@RequestBody @Valid SensorRequest request) {
        return ResponseEntity.ok(service.updateSensor(request));
    }

    @PatchMapping("/modo")
    public ResponseEntity<LightingResponse> updateMode(@RequestBody @Valid ModeRequest request) {
        return ResponseEntity.ok(service.updateMode(request));
    }

    @PatchMapping("/threshold")
    public ResponseEntity<LightingResponse> updateThreshold(@RequestBody @Valid ThresholdRequest request) {
        return ResponseEntity.ok(service.updateThreshold(request));
    }

    @PatchMapping("/interruptor")
    public ResponseEntity<LightingResponse> updateSwitch(@RequestBody @Valid SwitchRequest request) {
        return ResponseEntity.ok(service.updateSwitch(request));
    }

}
