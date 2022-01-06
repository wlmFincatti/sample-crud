package br.com.wlmfincatti.message.adapter.external;

import br.com.wlmfincatti.message.adapter.external.jpa.MessageRepository;
import br.com.wlmfincatti.message.adapter.external.jpa.model.MessageEntity;
import br.com.wlmfincatti.message.adapter.mapper.MessageMapper;
import br.com.wlmfincatti.message.core.domain.Message;
import br.com.wlmfincatti.message.core.usecase.port.MessageGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class MessageGatewayImpl implements MessageGateway {

    private final MessageRepository repository;
    private final MessageMapper messageMapper;

    @Override
    public Message saveMessage(Message message) {
        final MessageEntity messageEntity = repository.save(messageMapper.convertMessageToMessageEntity(message));
        return messageMapper.convertMessageEntityToMessage(messageEntity);
    }

    @Override
    public List<Message> findMessagesByConversationId(UUID conversationId) {
        final List<MessageEntity> messageEntities = repository.findByConversationId(conversationId);
        return messageMapper.convertMessageEntityToListMessage(messageEntities);
    }

    @Override
    public Optional<Message> findMessageById(UUID id) {
        return repository.findById(id).map(messageMapper::convertMessageEntityToMessage);
    }
}
