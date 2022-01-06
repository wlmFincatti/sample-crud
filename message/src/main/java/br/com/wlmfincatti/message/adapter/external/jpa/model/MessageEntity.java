package br.com.wlmfincatti.message.adapter.external.jpa.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "message")
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;
    @Column(name = "message_from")
    private UUID from;
    @Column(name = "message_to")
    private UUID to;
    @Column(name = "conversation_id")
    private UUID conversationId;
    @Column(name = "text")
    private String text;
    @Column(name = "date_conversation")
    private LocalDateTime dateConversation;

}
