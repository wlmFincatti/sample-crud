package br.com.wlmfincatti.message.core.domain.exception;

import static java.lang.String.format;

public class MesssageNotFoundException extends RuntimeException {

    public MesssageNotFoundException(final String id) {
        super(format("Message not found by id: [%s]", id));
    }

}
