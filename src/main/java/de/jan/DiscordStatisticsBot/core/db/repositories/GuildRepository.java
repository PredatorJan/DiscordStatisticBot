package de.jan.DiscordStatisticsBot.core.db.repositories;

import de.jan.DiscordStatisticsBot.core.db.entities.Guild;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface GuildRepository extends JpaRepository<Guild, Long> {

    Guild findByDiscordId(String id);

    Set<Guild> findByNameContains(String name);
}
