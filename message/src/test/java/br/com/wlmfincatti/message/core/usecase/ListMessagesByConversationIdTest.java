package br.com.wlmfincatti.message.core.usecase;

import br.com.wlmfincatti.message.core.domain.Message;
import br.com.wlmfincatti.message.core.usecase.port.MessageGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static br.com.wlmfincatti.message.core.fixture.fixture.MessageFixture.gimmeAListOfMessages;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ListMessagesByConversationIdTest {

    @Mock
    private MessageGateway gateway;

    @InjectMocks
    private ListMessagesByConversationId listMessagesByConversationId;

    @Test
    @DisplayName("Should list all message by conversation id")
    void execute() {
        final UUID id = UUID.randomUUID();
        final List<Message> messages = gimmeAListOfMessages(id);
        when(gateway.findMessagesByConversationId(id)).thenReturn(messages);

        final List<Message> result = listMessagesByConversationId.execute(id);

        assertNotNull(result);
        assertThat(result.size(), is(equalTo(1)));
        assertThat(result.get(0).getConversationId(), is(equalTo(id)));
        assertThat(result.get(0).getId(), is(equalTo(id)));
        assertThat(result.get(0).getFrom(), is(equalTo(id)));
        assertThat(result.get(0).getTo(), is(equalTo(id)));
        assertThat(result.get(0).getText(), is(equalTo("i can help you?")));
        verify(gateway, times(1)).findMessagesByConversationId(id);
    }
}