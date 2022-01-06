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
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateBotTest {

    @Mock
    private BotGateway gateway;

    @Mock
    private FindBotById findBotById;

    @InjectMocks
    private UpdateBot updateBot;

    @Test
    @DisplayName("Should update bot")
    void shouldUpdateBot() {
        final UUID id = UUID.randomUUID();
        final Bot bot = gimmeABot(id);
        when(gateway.save(bot)).thenReturn(bot);
        when(findBotById.execute(id)).thenReturn(bot);

        final Bot result = updateBot.execute(id, bot);

        assertNotNull(result);
        assertThat(result.getName(), is(equalTo("test")));
        assertThat(result.getId(), is(equalTo(id)));
        verify(gateway, times(1)).save(bot);
        verify(findBotById, times(1)).execute(id);
    }
}