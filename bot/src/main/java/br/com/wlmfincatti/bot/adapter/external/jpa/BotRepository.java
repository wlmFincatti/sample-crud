package br.com.wlmfincatti.bot.adapter.external.jpa;

import br.com.wlmfincatti.bot.adapter.external.jpa.model.BotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BotRepository extends JpaRepository<BotEntity, UUID> {
}
