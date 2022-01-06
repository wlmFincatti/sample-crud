package br.com.wlmfincatti.message.core.fixture.fixture;

import br.com.wlmfincatti.message.adapter.external.jpa.model.MessageEntity;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.UUID;

@UtilityClass
public class MessageEntityFixture {

    public static MessageEntity gimmeAMessageEntity(final UUID id) {
        return MessageEntity.builder()
                .id(id)
                .conversationId(id)
                .from(id)
                .to(id)
                .text("i can help you?")
                .dateConversation(LocalDateTime.of(2022, Month.JANUARY, 1, 12, 12))
                .build();
    }

    public static List<MessageEntity> gimmeAListOfMessageEntities(final UUID id) {
        return List.of(gimmeAMessageEntity(id));
    }
}
