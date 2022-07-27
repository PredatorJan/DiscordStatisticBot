package de.jan.DiscordStatisticsBot.core.db.repositories;

import de.jan.DiscordStatisticsBot.core.db.entities.UserGuildInformation;
import de.jan.DiscordStatisticsBot.core.db.entities.compositeIds.UserGuildInformationPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface UserGuildInformationRepository extends JpaRepository<UserGuildInformation, UserGuildInformationPK> {

    Set<UserGuildInformation> findByUserId(long userId);

    Page<UserGuildInformation> findByUserId(long userId, Pageable pageable);

    Set<UserGuildInformation> findByGuildId(long guildId);

}
