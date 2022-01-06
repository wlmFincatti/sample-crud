package br.com.wlmfincatti.bot.fixture;

import br.com.wlmfincatti.bot.adapter.entrypoint.model.BotResponse;
import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class BotResponsetFixture {

    public static BotResponse gimmeABotResponse(final UUID id) {
        return BotResponse.builder().id(id).name("test").build();
    }
}
