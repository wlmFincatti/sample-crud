package br.com.wlmfincatti.message.core.usecase;

import br.com.wlmfincatti.message.core.domain.Message;
import br.com.wlmfincatti.message.core.usecase.port.MessageGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class ListMessagesByConversationId {

    private final MessageGateway gateway;

    public List<Message> execute(final UUID conversationId) {
        log.info("[ListMessagesByConversationId] - Find messages by conversationId [{}]", conversationId);
        final List<Message> messages = gateway.findMessagesByConversationId(conversationId);
        log.info("[ListMessagesByConversationId] - messages found [{}]", messages);
        return messages;
    }

}
