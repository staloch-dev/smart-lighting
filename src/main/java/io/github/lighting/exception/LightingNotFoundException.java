package io.github.lighting.exception;

public class LightingNotFoundException extends RuntimeException {

    public LightingNotFoundException() {
        super("Lighting state not found");
    }
}
