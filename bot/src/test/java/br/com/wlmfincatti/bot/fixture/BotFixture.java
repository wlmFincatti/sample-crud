package br.com.wlmfincatti.bot.fixture;

import br.com.wlmfincatti.bot.core.domain.Bot;
import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class BotFixture {

    public static Bot gimmeABot(final UUID id){
        return Bot.builder().id(id).name("test").build();
    }
}
