package br.com.wlmfincatti.message.core.usecase;

import br.com.wlmfincatti.message.core.domain.Message;
import br.com.wlmfincatti.message.core.domain.exception.MesssageNotFoundException;
import br.com.wlmfincatti.message.core.usecase.port.MessageGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class FindMessageById {

    private final MessageGateway gateway;

    public Message execute(final UUID id) {
        log.info("[FindMessageById] - Find message by id [{}]", id);
        final Message messageById = gateway.findMessageById(id)
                .orElseThrow(() -> {
                    log.error("[FindMessageById] - Message not found by id [{}]", id);
                    return new MesssageNotFoundException(id.toString());
                });
        log.info("[FindMessageById] - message found [{}]", messageById);
        return messageById;
    }

}
