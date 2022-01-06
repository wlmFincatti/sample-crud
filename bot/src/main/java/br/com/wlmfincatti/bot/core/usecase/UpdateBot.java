package br.com.wlmfincatti.bot.core.usecase;

import br.com.wlmfincatti.bot.core.domain.Bot;
import br.com.wlmfincatti.bot.core.usecase.port.BotGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class UpdateBot {

    private final BotGateway gateway;
    private final FindBotById findBotById;

    public Bot execute(final UUID id, final Bot bot) {
        log.info("[UpdateBot] - Update Bot [{}]", bot);
        final Bot botFound = findBotById.execute(id);

        botFound.setName(bot.getName());

        final Bot botUpdated = gateway.save(botFound);

        log.info("[UpdateBot] - Bot Updated [{}]", botUpdated);
        return botUpdated;
    }
}
