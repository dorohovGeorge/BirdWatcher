package com.example.birdwatcher.model.exception;

import java.text.MessageFormat;

public class NestNotFoundException extends RuntimeException{
    public NestNotFoundException(final long id) {
        super(MessageFormat.format("Could not find nest with id: {0}", id));
    }
}
