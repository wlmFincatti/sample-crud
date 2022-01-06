package br.com.wlmfincatti.message.adapter.entrypoint.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class MessageResponse {

    @NotBlank(message = "text of message can not be empty or null")
    @JsonProperty("text")
    @Schema(name = "text", description = "text of message")
    private String text;

    @NotNull(message = "conversationId can not be null")
    @JsonProperty("conversationId")
    @Schema(name = "conversationId", description = "id to register conversation")
    private UUID conversationId;

    @NotNull(message = "from can not be null")
    @JsonProperty("from")
    @Schema(name = "from")
    private UUID from;

    @NotNull(message = "to can not be null")
    @JsonProperty("to")
    @Schema(name = "to")
    private UUID to;

    @JsonProperty("dateConversation")
    @Schema(name = "dateConversation", description = "date of conversation")
    @Builder.Default
    private LocalDateTime dateConversation = LocalDateTime.now();
}
