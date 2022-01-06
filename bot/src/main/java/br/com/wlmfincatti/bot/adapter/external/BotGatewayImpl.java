package br.com.wlmfincatti.bot.adapter.external;

import br.com.wlmfincatti.bot.adapter.external.jpa.BotRepository;
import br.com.wlmfincatti.bot.adapter.external.jpa.model.BotEntity;
import br.com.wlmfincatti.bot.adapter.mapper.BotMapper;
import br.com.wlmfincatti.bot.core.domain.Bot;
import br.com.wlmfincatti.bot.core.usecase.port.BotGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class BotGatewayImpl implements BotGateway {

    private final BotRepository repository;
    private final BotMapper mapper;

    @Override
    public Optional<Bot> findById(UUID id) {
        return repository.findById(id).map(mapper::convertBotEntityToBot);
    }

    @Override
    public Bot save(Bot bot) {
        final BotEntity botEntity = mapper.convertBotToBotEntity(bot);
        return mapper.convertBotEntityToBot(repository.save(botEntity));
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
