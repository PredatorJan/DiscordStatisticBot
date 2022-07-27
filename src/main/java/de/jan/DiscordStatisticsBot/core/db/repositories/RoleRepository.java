package de.jan.DiscordStatisticsBot.core.db.repositories;

import de.jan.DiscordStatisticsBot.core.db.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
