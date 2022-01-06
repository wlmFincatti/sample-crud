package br.com.wlmfincatti.message.adapter.entrypoint;

import br.com.wlmfincatti.message.adapter.entrypoint.model.MessageRequest;
import br.com.wlmfincatti.message.adapter.entrypoint.model.MessageResponse;
import br.com.wlmfincatti.message.adapter.mapper.MessageMapper;
import br.com.wlmfincatti.message.core.domain.Message;
import br.com.wlmfincatti.message.core.usecase.CreateMessage;
import br.com.wlmfincatti.message.core.usecase.FindMessageById;
import br.com.wlmfincatti.message.core.usecase.ListMessagesByConversationId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static br.com.wlmfincatti.message.adapter.entrypoint.config.Routes.PATH_MESSAGE;

@Validated
@Tag(name = "Message", description = "Crud Message")
@RequiredArgsConstructor
@RequestMapping(PATH_MESSAGE)
@RestController
public class MessageController {

    private final FindMessageById findMessageById;
    private final CreateMessage createMessage;
    private final ListMessagesByConversationId listMessagesByConversationId;
    private final MessageMapper messageMapper;

    @Operation(summary = "Find message by id",
            description = "Return message by id",
            responses = {@ApiResponse(responseCode = "200", description = "Sucess",
                    content = @Content(schema = @Schema(implementation = MessageResponse.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "404", description = "Not Found",
                            content = @Content(schema = @Schema(implementation = String.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE))})
    @GetMapping(path = "/{id}")
    public ResponseEntity<MessageResponse> getMessageById(@PathVariable final UUID id) {
        final Message message = findMessageById.execute(id);
        return ResponseEntity.ok(messageMapper.convertMessageToMessageResponse(message));
    }

    @Operation(summary = "Find messages by conversation id",
            description = "Return messages",
            responses = {@ApiResponse(responseCode = "200", description = "Sucess",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = MessageResponse.class)),
                            mediaType = MediaType.APPLICATION_JSON_VALUE))})
    @GetMapping
    public ResponseEntity<List<MessageResponse>> getMessagesByConversationId(@RequestParam final UUID conversationId) {
        final List<Message> messages = listMessagesByConversationId.execute(conversationId);
        return ResponseEntity.ok(messageMapper.convertMessageToListMessageResponse(messages));
    }

    @Operation(summary = "Create Message",
            description = "Create Message and return resource",
            responses = {@ApiResponse(responseCode = "201", description = "Created",
                    content = @Content(schema = @Schema(implementation = MessageResponse.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "400", description = "Bad Request",
                            content = @Content(schema = @Schema(implementation = Map.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE))})
    @PostMapping
    public ResponseEntity<MessageResponse> createMessage(@Valid @RequestBody final MessageRequest messageRequest,
                                                         UriComponentsBuilder uriComponentsBuilder) {

        final Message message = createMessage
                .execute(messageMapper.convertMessageRequestToMessage(messageRequest));

        UriComponents uriComponents = uriComponentsBuilder.path(PATH_MESSAGE.concat("/{id}"))
                .buildAndExpand(message.getId());

        return ResponseEntity.created(uriComponents.toUri())
                .body(messageMapper.convertMessageToMessageResponse(message));
    }

}
