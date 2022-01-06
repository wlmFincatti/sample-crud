package br.com.wlmfincatti.bot.adapter.mapper;

import br.com.wlmfincatti.bot.adapter.entrypoint.BotController;
import br.com.wlmfincatti.bot.adapter.entrypoint.model.BotRequest;
import br.com.wlmfincatti.bot.adapter.entrypoint.model.BotResponse;
import br.com.wlmfincatti.bot.adapter.external.BotGatewayImpl;
import br.com.wlmfincatti.bot.adapter.external.jpa.model.BotEntity;
import br.com.wlmfincatti.bot.core.domain.Bot;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {BotGatewayImpl.class, BotController.class})
public interface BotMapper {

    Bot convertBotEntityToBot(final BotEntity botEntity);

    Bot convertBotRequestToBot(final BotRequest botRequest);

    BotEntity convertBotToBotEntity(final Bot bot);

    BotResponse convertBotToBotResponse(final Bot bot);
}
