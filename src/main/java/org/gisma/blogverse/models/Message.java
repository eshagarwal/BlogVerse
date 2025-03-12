package org.gisma.blogverse.models;

import org.gisma.blogverse.exceptions.BadArgumentException;

public record Message(String message) {
    public Message {
        if (message == null || message.isBlank()) {
            throw new BadArgumentException("Message cannot be null or empty");
        }
    }
}
