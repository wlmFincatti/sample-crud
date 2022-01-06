package br.com.wlmfincatti.message.core.usecase.port;

import br.com.wlmfincatti.message.core.domain.Message;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MessageGateway {

    Message saveMessage(final Message message);

    List<Message> findMessagesByConversationId(final UUID conversationId);

    Optional<Message> findMessageById(final UUID id);
}
