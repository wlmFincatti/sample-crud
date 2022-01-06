package br.com.wlmfincatti.bot.core.usecase;

import br.com.wlmfincatti.bot.core.usecase.port.BotGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class DeleteBotById {

    private final BotGateway gateway;
    private final FindBotById findBotById;

    public void execute(final UUID id) {
        log.info("[DeleteBotById] - Delete bot by id [{}]", id);
        findBotById.execute(id);

        gateway.deleteById(id);
        log.info("[DeleteBotById] - Bot deleted - id [{}]", id);
    }
}
