package com.example.birdwatcher.model.exception;

import com.example.birdwatcher.model.Bird;

import java.text.MessageFormat;

public class BirdIsAlreadyAssignedException extends RuntimeException {
    public BirdIsAlreadyAssignedException(final Bird bird, final long idNest) {
        super(MessageFormat.format("Item: {0} is already assigned to nest: {1}", bird, idNest));
    }
}
