package br.com.wlmfincatti.bot.core.domain.exception;

import static java.lang.String.format;

public class BotNotFoundException extends RuntimeException {

    public BotNotFoundException(final String id) {
        super(format("Bot not found by id: [%s]", id));
    }

}
