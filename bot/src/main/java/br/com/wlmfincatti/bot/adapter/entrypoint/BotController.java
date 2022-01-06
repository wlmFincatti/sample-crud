package br.com.wlmfincatti.bot.adapter.entrypoint;

import br.com.wlmfincatti.bot.adapter.entrypoint.model.BotRequest;
import br.com.wlmfincatti.bot.adapter.entrypoint.model.BotResponse;
import br.com.wlmfincatti.bot.adapter.mapper.BotMapper;
import br.com.wlmfincatti.bot.core.domain.Bot;
import br.com.wlmfincatti.bot.core.usecase.CreateBot;
import br.com.wlmfincatti.bot.core.usecase.DeleteBotById;
import br.com.wlmfincatti.bot.core.usecase.FindBotById;
import br.com.wlmfincatti.bot.core.usecase.UpdateBot;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;

import static br.com.wlmfincatti.bot.adapter.entrypoint.config.Routes.PATH_BOT;

@Tag(name = "Bot", description = "Crud Bot")
@RequiredArgsConstructor
@RequestMapping(PATH_BOT)
@RestController
public class BotController {

    private final FindBotById findById;
    private final CreateBot createBot;
    private final DeleteBotById deleteBotById;
    private final UpdateBot updateBot;
    private final BotMapper mapper;

    @Operation(summary = "Get Bot by id",
            description = "Return Bot",
            responses = {@ApiResponse(responseCode = "200", description = "Sucess",
                    content = @Content(schema = @Schema(implementation = BotResponse.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "404", description = "Not Found",
                            content = @Content(schema = @Schema(implementation = String.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE))})
    @GetMapping(path = "/{id}")
    public ResponseEntity<BotResponse> getBotById(@PathVariable final UUID id) {
        return ResponseEntity.ok(mapper.convertBotToBotResponse(findById.execute(id)));
    }

    @Operation(summary = "Create Bot",
            description = "Create Bot and return resource",
            responses = {@ApiResponse(responseCode = "201", description = "Created",
                    content = @Content(schema = @Schema(implementation = BotResponse.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "400", description = "Bad Request",
                            content = @Content(schema = @Schema(implementation = Map.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE))})
    @PostMapping
    public ResponseEntity<BotResponse> createBot(@Valid @RequestBody final BotRequest botRequest,
                                                 final UriComponentsBuilder uriComponentsBuilder) {

        UriComponents uriComponents = uriComponentsBuilder.path(PATH_BOT.concat("/{id}"))
                .buildAndExpand(botRequest.getId());

        final Bot bot = createBot.execute(mapper.convertBotRequestToBot(botRequest));

        return ResponseEntity.created(uriComponents.toUri())
                .body(mapper.convertBotToBotResponse(bot));
    }

    @Operation(summary = "Delete Bot",
            description = "Delete Bot",
            responses = {@ApiResponse(responseCode = "204", description = "No Content")})
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteBot(@PathVariable final UUID id) {
        deleteBotById.execute(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update Bot",
            description = "Update Bot",
            responses = {@ApiResponse(responseCode = "200", description = "Sucess",
                    content = @Content(schema = @Schema(implementation = BotResponse.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "404", description = "Not Found",
                            content = @Content(schema = @Schema(implementation = String.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request",
                            content = @Content(schema = @Schema(implementation = Map.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE))})
    @PutMapping(path = "/{id}")
    public ResponseEntity<BotResponse> updateBot(@PathVariable final UUID id,
                                                 @Valid @RequestBody final BotRequest botRequest) {
        final Bot bot = updateBot.execute(id, mapper.convertBotRequestToBot(botRequest));
        return ResponseEntity.ok(mapper.convertBotToBotResponse(bot));
    }

}
