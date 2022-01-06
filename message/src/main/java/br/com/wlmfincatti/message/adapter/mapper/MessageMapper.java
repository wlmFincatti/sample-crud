package br.com.wlmfincatti.message.adapter.mapper;

import br.com.wlmfincatti.message.adapter.entrypoint.MessageController;
import br.com.wlmfincatti.message.adapter.entrypoint.model.MessageRequest;
import br.com.wlmfincatti.message.adapter.entrypoint.model.MessageResponse;
import br.com.wlmfincatti.message.adapter.external.MessageGatewayImpl;
import br.com.wlmfincatti.message.adapter.external.jpa.model.MessageEntity;
import br.com.wlmfincatti.message.core.domain.Message;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {MessageGatewayImpl.class, MessageController.class})
public interface MessageMapper {

    Message convertMessageRequestToMessage(final MessageRequest messageRequest);

    Message convertMessageEntityToMessage(final MessageEntity messageEntity);

    MessageEntity convertMessageToMessageEntity(final Message message);

    MessageResponse convertMessageToMessageResponse(final Message message);

    List<MessageResponse> convertMessageToListMessageResponse(final List<Message> messages);

    List<Message> convertMessageEntityToListMessage(List<MessageEntity> messageEntities);
}
