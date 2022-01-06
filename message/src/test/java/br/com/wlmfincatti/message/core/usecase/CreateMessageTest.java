package br.com.wlmfincatti.message.core.usecase;

import br.com.wlmfincatti.message.core.domain.Message;
import br.com.wlmfincatti.message.core.usecase.port.MessageGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static br.com.wlmfincatti.message.core.fixture.fixture.MessageFixture.gimmeAMessage;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateMessageTest {

    @Mock
    private MessageGateway gateway;

    @InjectMocks
    private CreateMessage createMessage;

    @Test
    @DisplayName("Should create message")
    void shouldCreateMessage() {
        final UUID id = UUID.randomUUID();
        final Message message = gimmeAMessage(id);
        when(gateway.saveMessage(message)).thenReturn(message);

        final Message result = createMessage.execute(message);

        assertNotNull(result);
        assertThat(result.getConversationId(), is(equalTo(id)));
        assertThat(result.getId(), is(equalTo(id)));
        assertThat(result.getFrom(), is(equalTo(id)));
        assertThat(result.getTo(), is(equalTo(id)));
        assertThat(result.getText(), is(equalTo("i can help you?")));
        verify(gateway, times(1)).saveMessage(message);
    }
}