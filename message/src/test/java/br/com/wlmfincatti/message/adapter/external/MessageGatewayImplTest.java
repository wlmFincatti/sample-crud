package br.com.wlmfincatti.message.adapter.external;

import br.com.wlmfincatti.message.adapter.external.jpa.MessageRepository;
import br.com.wlmfincatti.message.adapter.external.jpa.model.MessageEntity;
import br.com.wlmfincatti.message.adapter.mapper.MessageMapper;
import br.com.wlmfincatti.message.core.domain.Message;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static br.com.wlmfincatti.message.core.fixture.fixture.MessageEntityFixture.gimmeAListOfMessageEntities;
import static br.com.wlmfincatti.message.core.fixture.fixture.MessageEntityFixture.gimmeAMessageEntity;
import static br.com.wlmfincatti.message.core.fixture.fixture.MessageFixture.gimmeAListOfMessages;
import static br.com.wlmfincatti.message.core.fixture.fixture.MessageFixture.gimmeAMessage;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MessageGatewayImplTest {

    @Mock
    private MessageRepository repository;

    @Mock
    private MessageMapper messageMapper;

    @InjectMocks
    private MessageGatewayImpl gateway;

    @Test
    @DisplayName("Should save message")
    void shouldSaveMessage() {
        final UUID id = UUID.randomUUID();
        final Message message = gimmeAMessage(id);
        final MessageEntity messageEntity = gimmeAMessageEntity(id);
        when(repository.save(messageEntity)).thenReturn(messageEntity);
        when(messageMapper.convertMessageEntityToMessage(messageEntity)).thenReturn(message);
        when(messageMapper.convertMessageToMessageEntity(message)).thenReturn(messageEntity);

        final Message result = gateway.saveMessage(message);

        assertNotNull(result);
        assertThat(result.getConversationId(), is(equalTo(id)));
        assertThat(result.getId(), is(equalTo(id)));
        assertThat(result.getFrom(), is(equalTo(id)));
        assertThat(result.getTo(), is(equalTo(id)));
        assertThat(result.getText(), is(equalTo("i can help you?")));
        verify(repository, times(1)).save(messageEntity);
        verify(messageMapper, times(1)).convertMessageEntityToMessage(messageEntity);
        verify(messageMapper, times(1)).convertMessageToMessageEntity(message);
    }

    @Test
    @DisplayName("Should find messages by conversation id")
    void shouldFindMessagesByConversationId() {
        final UUID id = UUID.randomUUID();
        final List<Message> messages = gimmeAListOfMessages(id);
        final List<MessageEntity> messageEntities = gimmeAListOfMessageEntities(id);
        when(repository.findByConversationId(id)).thenReturn(messageEntities);
        when(messageMapper.convertMessageEntityToListMessage(messageEntities)).thenReturn(messages);

        final List<Message> result = gateway.findMessagesByConversationId(id);

        assertNotNull(result);
        assertThat(result.get(0).getConversationId(), is(equalTo(id)));
        assertThat(result.get(0).getId(), is(equalTo(id)));
        assertThat(result.get(0).getFrom(), is(equalTo(id)));
        assertThat(result.get(0).getTo(), is(equalTo(id)));
        assertThat(result.get(0).getText(), is(equalTo("i can help you?")));
        verify(repository, times(1)).findByConversationId(id);
        verify(messageMapper, times(1)).convertMessageEntityToListMessage(messageEntities);
    }

    @Test
    @DisplayName("Should find message by id")
    void shouldFindMessageById() {
        final UUID id = UUID.randomUUID();
        final Message message = gimmeAMessage(id);
        final MessageEntity messageEntity = gimmeAMessageEntity(id);
        when(repository.findById(id)).thenReturn(Optional.of(messageEntity));
        when(messageMapper.convertMessageEntityToMessage(messageEntity)).thenReturn(message);

        final Message result = gateway.findMessageById(id).get();

        assertNotNull(result);
        assertThat(result.getConversationId(), is(equalTo(id)));
        assertThat(result.getId(), is(equalTo(id)));
        assertThat(result.getFrom(), is(equalTo(id)));
        assertThat(result.getTo(), is(equalTo(id)));
        assertThat(result.getText(), is(equalTo("i can help you?")));
        verify(repository, times(1)).findById(id);
        verify(messageMapper, times(1)).convertMessageEntityToMessage(messageEntity);
    }
}