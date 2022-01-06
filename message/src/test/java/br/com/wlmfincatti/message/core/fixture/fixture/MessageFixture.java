package br.com.wlmfincatti.message.core.fixture.fixture;

import br.com.wlmfincatti.message.core.domain.Message;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.UUID;

@UtilityClass
public class MessageFixture {

    public static Message gimmeAMessage(final UUID id) {
        return Message.builder()
                .id(id)
                .conversationId(id)
                .from(id)
                .to(id)
                .text("i can help you?")
                .dateConversation(LocalDateTime.of(2022, Month.JANUARY, 1, 12, 12))
                .build();
    }

    public static List<Message> gimmeAListOfMessages(final UUID id) {
        return List.of(gimmeAMessage(id));
    }
}
