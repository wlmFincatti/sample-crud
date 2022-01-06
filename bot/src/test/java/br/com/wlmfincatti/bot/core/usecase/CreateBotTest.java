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
class CreateBotTest {

    @Mock
    private BotGateway gateway;

    @InjectMocks
    private CreateBot createBot;

    @Test
    @DisplayName("Should create bot")
    void shouldCreateBot() {
        final UUID id = UUID.randomUUID();
        final Bot bot = gimmeABot(id);
        when(gateway.save(bot)).thenReturn(bot);

        final Bot result = createBot.execute(bot);

        assertNotNull(result);
        verify(gateway, times(1)).save(bot);
        assertThat(result.getName(), is(equalTo("test")));
        assertThat(result.getId(), is(equalTo(id)));
    }
}