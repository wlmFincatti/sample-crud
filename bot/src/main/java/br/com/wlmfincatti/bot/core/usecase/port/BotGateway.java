package br.com.wlmfincatti.bot.core.usecase.port;

import br.com.wlmfincatti.bot.core.domain.Bot;

import java.util.Optional;
import java.util.UUID;

public interface BotGateway {

    Optional<Bot> findById(final UUID id);

    Bot save(final Bot bot);

    void deleteById(final UUID id);

}
