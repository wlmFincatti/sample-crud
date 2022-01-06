package br.com.wlmfincatti.message.adapter.external.jpa;

import br.com.wlmfincatti.message.adapter.external.jpa.model.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, UUID> {

    List<MessageEntity> findByConversationId(final UUID conversationId);
}
