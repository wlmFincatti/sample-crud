package br.com.wlmfincatti.bot.adapter.mapper;

import br.com.wlmfincatti.bot.adapter.entrypoint.model.BotRequest;
import br.com.wlmfincatti.bot.adapter.entrypoint.model.BotResponse;
import br.com.wlmfincatti.bot.adapter.external.jpa.model.BotEntity;
import br.com.wlmfincatti.bot.core.domain.Bot;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

import static br.com.wlmfincatti.bot.fixture.BotEntityFixture.gimmeABotEntity;
import static br.com.wlmfincatti.bot.fixture.BotFixture.gimmeABot;
import static br.com.wlmfincatti.bot.fixture.BotRequestFixture.gimmeABotRequest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BotMapperTest {

    private final BotMapper botMapper = Mappers.getMapper(BotMapper.class);

    @Test
    void convertBotEntityToBot() {
        final UUID id = UUID.randomUUID();
        final BotEntity botEntity = gimmeABotEntity(id);

        final Bot bot = botMapper.convertBotEntityToBot(botEntity);

        assertNotNull(bot);
        assertThat(bot.getId(), is(equalTo(id)));
        assertThat(bot.getName(), is(equalTo("test")));
    }

    @Test
    void convertBotRequestToBot() {
        final UUID id = UUID.randomUUID();
        final BotRequest botRequest = gimmeABotRequest(id);

        final Bot bot = botMapper.convertBotRequestToBot(botRequest);

        assertNotNull(bot);
        assertThat(bot.getId(), is(equalTo(id)));
        assertThat(bot.getName(), is(equalTo("test")));
    }

    @Test
    void convertBotToBotEntity() {
        final UUID id = UUID.randomUUID();
        final Bot bot = gimmeABot(id);

        final BotEntity botEntity = botMapper.convertBotToBotEntity(bot);

        assertNotNull(botEntity);
        assertThat(botEntity.getId(), is(equalTo(id)));
        assertThat(botEntity.getName(), is(equalTo("test")));
    }

    @Test
    void convertBotToBotResponse() {
        final UUID id = UUID.randomUUID();
        final Bot bot = gimmeABot(id);

        final BotResponse botResponse = botMapper.convertBotToBotResponse(bot);

        assertNotNull(botResponse);
        assertThat(botResponse.getId(), is(equalTo(id)));
        assertThat(botResponse.getName(), is(equalTo("test")));
    }
}