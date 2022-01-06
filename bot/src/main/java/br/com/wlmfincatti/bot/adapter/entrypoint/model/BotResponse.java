package br.com.wlmfincatti.bot.adapter.entrypoint.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class BotResponse {

    @Schema(name = "id")
    private UUID id;
    @Schema(name = "name", description = "name of bot")
    private String name;

}
