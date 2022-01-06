package br.com.wlmfincatti.message.core.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@ToString
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Message {

    private UUID id;
    private String text;
    private UUID conversationId;
    private UUID from;
    private UUID to;
    private LocalDateTime dateConversation;

}
