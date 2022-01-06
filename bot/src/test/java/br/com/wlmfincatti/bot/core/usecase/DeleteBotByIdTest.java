package br.com.wlmfincatti.bot.core.usecase;

import br.com.wlmfincatti.bot.core.domain.Bot;
import br.com.wlmfincatti.bot.core.usecase.port.BotGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static br.com.wlmfincatti.bot.fixture.BotFixture.gimmeABot;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteBotByIdTest {

    @Mock
    private BotGateway gateway;

    @Mock
    private FindBotById findBotById;

    @InjectMocks
    private DeleteBotById deleteBotById;

    @Test
    @DisplayName("Should delete bot")
    void shouldDeleteBot() {
        final UUID id = UUID.randomUUID();
        final Bot bot = gimmeABot(id);
        doNothing().when(gateway).deleteById(id);
        when(findBotById.execute(id)).thenReturn(bot);

        deleteBotById.execute(id);

        verify(gateway, times(1)).deleteById(id);
        verify(findBotById, times(1)).execute(id);
    }
}