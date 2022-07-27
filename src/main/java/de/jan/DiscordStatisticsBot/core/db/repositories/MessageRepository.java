package de.jan.DiscordStatisticsBot.core.db.repositories;

import de.jan.DiscordStatisticsBot.core.db.entities.Guild;
import de.jan.DiscordStatisticsBot.core.db.entities.Message;
import de.jan.DiscordStatisticsBot.core.db.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.Set;

public interface MessageRepository extends JpaRepository<Message, Long> {

    Set<Message> findByUserAndGuildAndTimestampBetween(User user, Guild guild, Timestamp startTimestamp, Timestamp endTimestamp);

    long deleteByTimestampLessThan(Timestamp timestamp);

    Set<Message> findByUser(User user);

    Set<Message> findByGuild(Guild guild);

    Set<Message> findByUserAndGuild(User user, Guild guild);
}
