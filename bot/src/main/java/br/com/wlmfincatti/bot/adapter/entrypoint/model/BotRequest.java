package br.com.wlmfincatti.bot.adapter.entrypoint.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class BotRequest {

    @NotNull(message = "id can not be null")
    @JsonProperty("id")
    @Schema(name = "id")
    private UUID id;

    @NotBlank(message = "name of bot can not be empty or null")
    @JsonProperty("name")
    @Schema(name = "name", description = "name of bot")
    private String name;

}
