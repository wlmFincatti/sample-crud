package br.com.wlmfincatti.bot.fixture;

import br.com.wlmfincatti.bot.adapter.entrypoint.model.BotRequest;
import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class BotRequestFixture {

    public static BotRequest gimmeABotRequest(final UUID id) {
        return BotRequest.builder().id(id).name("test").build();
    }
}
