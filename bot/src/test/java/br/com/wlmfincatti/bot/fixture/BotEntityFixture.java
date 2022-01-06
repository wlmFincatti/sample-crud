package br.com.wlmfincatti.bot.fixture;

import br.com.wlmfincatti.bot.adapter.external.jpa.model.BotEntity;
import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class BotEntityFixture {

    public static BotEntity gimmeABotEntity(final UUID id) {
        return BotEntity.builder().id(id).name("test").build();
    }
}
