package io.github.lighting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class LightingApplication {

	public static void main(String[] args) {
		SpringApplication.run(LightingApplication.class, args);
		log.info("Application started successfully!");
	}

}
