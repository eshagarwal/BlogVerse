package org.gisma.sochvichaar.models;

import org.gisma.sochvichaar.exceptions.BadArgumentException;

public record Message(String message) {
    public Message {
        if (message == null || message.isBlank()) {
            throw new BadArgumentException("Message cannot be null or empty");
        }
    }
}
