package br.com.wlmfincatti.message.core.usecase;

import br.com.wlmfincatti.message.core.domain.Message;
import br.com.wlmfincatti.message.core.domain.exception.MesssageNotFoundException;
import br.com.wlmfincatti.message.core.usecase.port.MessageGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static br.com.wlmfincatti.message.core.fixture.fixture.MessageFixture.gimmeAMessage;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindMessageByIdTest {

    @Mock
    private MessageGateway gateway;

    @InjectMocks
    private FindMessageById findMessageById;

    @Test
    @DisplayName("Should find message by id")
    void ShouldFindMessageById() {
        final UUID id = UUID.randomUUID();
        final Message message = gimmeAMessage(id);
        when(gateway.findMessageById(id)).thenReturn(Optional.of(message));

        final Message result = findMessageById.execute(id);

        assertNotNull(result);
        assertThat(result.getConversationId(), is(equalTo(id)));
        assertThat(result.getId(), is(equalTo(id)));
        assertThat(result.getFrom(), is(equalTo(id)));
        assertThat(result.getTo(), is(equalTo(id)));
        assertThat(result.getText(), is(equalTo("i can help you?")));
        verify(gateway, times(1)).findMessageById(id);
    }


    @Test
    @DisplayName("Should not find message by id")
    void ShouldNotFindMessageById() {
        final UUID id = UUID.randomUUID();
        when(gateway.findMessageById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(MesssageNotFoundException.class, () -> {
            findMessageById.execute(id);
        });

        assertThat(exception.getMessage(),
                is(equalTo(String.format("Message not found by id: [%s]", id))));
        verify(gateway, times(1)).findMessageById(id);
    }
}