package de.jan.DiscordStatisticsBot.core.db.services;

import de.jan.DiscordStatisticsBot.core.db.entities.UserGuildInformation;
import de.jan.DiscordStatisticsBot.core.db.entities.compositeIds.UserGuildInformationPK;
import de.jan.DiscordStatisticsBot.core.db.repositories.UserGuildInformationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserGuildInformationService {

    private Logger LOGGER = LoggerFactory.getLogger(UserGuildInformationService.class);

    @Autowired
    private UserGuildInformationRepository userGuildInformationRepository;

    public UserGuildInformation save(UserGuildInformation userGuildInformation) {
        LOGGER.trace("Save userGuildInformation: " + userGuildInformation);
        return userGuildInformationRepository.save(userGuildInformation);
    }

    public void delete(UserGuildInformation userGuildInformation) {
        LOGGER.trace("Delete userGuildInformation: " + userGuildInformation);
        userGuildInformationRepository.delete(userGuildInformation);
    }

    public UserGuildInformation getById(long userId, long guildId) {
        LOGGER.trace(String.format("Get userGuildInformation by userId %d and guildId %d", userId, guildId));
        return userGuildInformationRepository.findById(new UserGuildInformationPK(guildId, userId)).orElse(null);
    }

    public Set<UserGuildInformation> getAllByUserId(long userId) {
        LOGGER.trace(String.format("Get userGuildInformation by userId %d", userId));
        return userGuildInformationRepository.findByUserId(userId);
    }

    public List<UserGuildInformation> getAllByUserIdPageable(long userId, Pageable pageable) {
        LOGGER.trace(String.format("Get userGuildInformation by userId %d", userId));
        return userGuildInformationRepository.findByUserId(userId, pageable).getContent();
    }

    public Set<UserGuildInformation> getAllByGuildId(long guildId) {
        LOGGER.trace(String.format("Get userGuildInformation by userId %d", guildId));
        return userGuildInformationRepository.findByGuildId(guildId);
    }

    public Page<UserGuildInformation> getAllPageable(Pageable pageable) {
        return userGuildInformationRepository.findAll(pageable);
    }
}
