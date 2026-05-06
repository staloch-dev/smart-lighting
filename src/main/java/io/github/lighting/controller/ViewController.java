package io.github.lighting.controller;

import io.github.lighting.dto.request.ModeRequest;
import io.github.lighting.dto.request.SwitchRequest;
import io.github.lighting.dto.request.ThresholdRequest;
import io.github.lighting.enums.Mode;
import io.github.lighting.service.LightingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ViewController {

    private final LightingService service;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("state", service.getCurrentState());
        return "index";
    }

    @PostMapping("/modo")
    public String updateMode(@RequestParam String mode) {
        service.updateMode(new ModeRequest(Mode.valueOf(mode)));
        return "redirect:/";
    }

    @PostMapping("/interruptor")
    public String updateSwitch(@RequestParam Boolean switchOn) {
        service.updateSwitch(new SwitchRequest(switchOn));
        return "redirect:/";
    }

    @PostMapping("/threshold")
    public String updateThreshold(@RequestParam Integer threshold) {
        service.updateThreshold(new ThresholdRequest(threshold));
        return "redirect:/";
    }

}
