package io.github.lighting.config;

import io.github.lighting.domain.Lighting;
import io.github.lighting.enums.Mode;
import io.github.lighting.repository.LightingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final LightingRepository repository;

    @Override
    public void run(String... args) {
        if (repository.count() == 0) {
            repository.save(Lighting.builder()
                    .mode(Mode.AUTOMATIC)
                    .luminosity(0)
                    .ledOn(false)
                    .switchOn(false)
                    .threshold(500)
                    .build());
        }
    }
}
