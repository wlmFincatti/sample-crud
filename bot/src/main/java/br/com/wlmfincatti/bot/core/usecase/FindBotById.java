package br.com.wlmfincatti.bot.core.usecase;

import br.com.wlmfincatti.bot.core.domain.Bot;
import br.com.wlmfincatti.bot.core.domain.exception.BotNotFoundException;
import br.com.wlmfincatti.bot.core.usecase.port.BotGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class FindBotById {

    private final BotGateway gateway;

    public Bot execute(final UUID id) {
        log.info("[FindBotById] - Find bot by id [{}]", id);
        return gateway.findById(id)
                .orElseThrow(() -> {
                    log.error("[FindBotById] - Bot not found by id [{}]", id);
                    return new BotNotFoundException(id.toString());
                });
    }
}
