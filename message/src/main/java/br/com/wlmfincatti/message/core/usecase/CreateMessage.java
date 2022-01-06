package br.com.wlmfincatti.message.core.usecase;

import br.com.wlmfincatti.message.core.domain.Message;
import br.com.wlmfincatti.message.core.usecase.port.MessageGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CreateMessage {

    private final MessageGateway gateway;

    public Message execute(final Message message) {
        log.info("[CreateMessage] - Create message [{}]", message);
        final Message messageCreated = gateway.saveMessage(message);
        log.info("[CreateMessage] - message created [{}]", messageCreated);
        return messageCreated;
    }

}
