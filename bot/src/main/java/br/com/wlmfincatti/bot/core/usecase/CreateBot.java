package br.com.wlmfincatti.bot.core.usecase;

import br.com.wlmfincatti.bot.core.domain.Bot;
import br.com.wlmfincatti.bot.core.usecase.port.BotGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CreateBot {

    private final BotGateway gateway;

    public Bot execute(final Bot bot) {
        log.info("[CreateBot] - Create bot [{}]", bot);
        final Bot botCreated = gateway.save(bot);
        log.info("[CreateBot] - bot created [{}]", botCreated);
        return botCreated;
    }
}
