package de.jan.DiscordStatisticsBot.core.db.services;

import de.jan.DiscordStatisticsBot.core.db.entities.Guild;
import de.jan.DiscordStatisticsBot.core.db.entities.User;
import de.jan.DiscordStatisticsBot.core.db.repositories.GuildRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class GuildService implements BasicService<Guild> {

    private Logger LOGGER = LoggerFactory.getLogger(GuildService.class);

    @Autowired
    private GuildRepository guildRepository;

    @Override
    public Guild save(Guild guild) {
        LOGGER.trace("Save guild: " + guild);
        return guildRepository.save(guild);
    }

    @Override
    public void delete(Guild guild) {
        LOGGER.trace("Delete guild: " + guild);
        guildRepository.delete(guild);
    }

    @Override
    public Guild getById(long id) {
        LOGGER.trace(String.format("Get guild by id %d", id));
        return guildRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Guild> getAllPageable(Pageable pageable) {
        LOGGER.trace("Get all guilds pageable");
        return guildRepository.findAll(pageable);
    }

    @Override
    public long getCount() {
        LOGGER.trace("Get guild count");
        return guildRepository.count();
    }

    public Guild getGuild(long discordId) {
        LOGGER.trace(String.format("Get guild by discordid %d", discordId));
        return guildRepository.findByDiscordId(String.valueOf(discordId));
    }

    public Set<Guild> getGuildByName(String name) {
        LOGGER.trace(String.format("Get guild by name that contains %s", name));
        return guildRepository.findByNameContains(name);
    }
}
