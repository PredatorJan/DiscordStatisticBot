package de.jan.DiscordStatisticsBot.core.db.repositories;

import de.jan.DiscordStatisticsBot.core.db.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByDiscordid(String id);
}
