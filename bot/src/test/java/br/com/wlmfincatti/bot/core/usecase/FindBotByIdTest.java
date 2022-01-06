package br.com.wlmfincatti.bot.core.usecase;

import br.com.wlmfincatti.bot.core.domain.Bot;
import br.com.wlmfincatti.bot.core.domain.exception.BotNotFoundException;
import br.com.wlmfincatti.bot.core.usecase.port.BotGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static br.com.wlmfincatti.bot.fixture.BotFixture.gimmeABot;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindBotByIdTest {

    @Mock
    private BotGateway gateway;

    @InjectMocks
    private FindBotById findBotById;

    @Test
    @DisplayName("Should find bot by id")
    void shouldFindBotById() {
        final UUID id = UUID.randomUUID();
        final Bot bot = gimmeABot(id);
        when(gateway.findById(id)).thenReturn(Optional.of(bot));

        final Bot result = findBotById.execute(id);

        assertNotNull(result);
        assertThat(result.getName(), is(equalTo("test")));
        assertThat(result.getId(), is(equalTo(id)));
        verify(gateway, times(1)).findById(id);
    }

    @Test
    @DisplayName("Should not find bot by id")
    void shouldNotFindBotById() {
        final UUID id = UUID.randomUUID();
        when(gateway.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(BotNotFoundException.class, () -> {
            findBotById.execute(id);
        });

        assertThat(exception.getMessage(),
                is(equalTo(String.format("Bot not found by id: [%s]", id))));
        verify(gateway, times(1)).findById(id);
    }
}