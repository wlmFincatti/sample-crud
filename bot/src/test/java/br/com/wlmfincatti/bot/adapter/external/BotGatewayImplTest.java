package br.com.wlmfincatti.bot.adapter.external;

import br.com.wlmfincatti.bot.adapter.external.jpa.BotRepository;
import br.com.wlmfincatti.bot.adapter.external.jpa.model.BotEntity;
import br.com.wlmfincatti.bot.adapter.mapper.BotMapper;
import br.com.wlmfincatti.bot.core.domain.Bot;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static br.com.wlmfincatti.bot.fixture.BotEntityFixture.gimmeABotEntity;
import static br.com.wlmfincatti.bot.fixture.BotFixture.gimmeABot;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BotGatewayImplTest {

    private final static UUID BOT_ID = UUID.randomUUID();

    @Mock
    private BotRepository repository;

    @Mock
    private BotMapper mapper;

    @InjectMocks
    private BotGatewayImpl gateway;

    @DisplayName("Should find Bot by id [gateway]")
    @Test
    void findById() {
        final Bot bot = gimmeABot(BOT_ID);
        final BotEntity botEntity = gimmeABotEntity(BOT_ID);
        when(mapper.convertBotEntityToBot(botEntity)).thenReturn(bot);
        when(repository.findById(BOT_ID)).thenReturn(Optional.of(botEntity));

        final Bot result = gateway.findById(BOT_ID).get();

        assertThat(result.getId(), equalTo(BOT_ID));
        assertThat(result.getName(), equalTo("test"));
        verify(mapper, times(1)).convertBotEntityToBot(botEntity);
        verify(repository, times(1)).findById(BOT_ID);
    }

    @DisplayName("Should create Bot [gateway]")
    @Test
    void create() {
        final Bot bot = gimmeABot(BOT_ID);
        final BotEntity botEntity = gimmeABotEntity(BOT_ID);
        when(repository.save(botEntity)).thenReturn(botEntity);
        when(mapper.convertBotEntityToBot(botEntity)).thenReturn(bot);
        when(mapper.convertBotToBotEntity(bot)).thenReturn(botEntity);

        final Bot result = gateway.save(bot);

        assertThat(result.getId(), equalTo(BOT_ID));
        assertThat(result.getName(), equalTo("test"));
        verify(repository, times(1)).save(any());
        verify(mapper, times(1)).convertBotToBotEntity(bot);
        verify(mapper, times(1)).convertBotEntityToBot(botEntity);
    }

    @DisplayName("Should delete Bot by id [gateway]")
    @Test
    void deleteById() {
        gateway.deleteById(BOT_ID);

        verify(repository, times(1)).deleteById(BOT_ID);
    }
}