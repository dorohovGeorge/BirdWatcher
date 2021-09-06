package com.example.birdwatcher.model.exception;

import java.text.MessageFormat;

public class BirdNotFoundException extends RuntimeException {
    public BirdNotFoundException (long id) {
        super(MessageFormat.format("Could not find bird with id: {0}", id));
    }

}
