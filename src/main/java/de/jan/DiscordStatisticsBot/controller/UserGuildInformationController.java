package de.jan.DiscordStatisticsBot.controller;

import de.jan.DiscordStatisticsBot.core.db.entities.Role;
import de.jan.DiscordStatisticsBot.core.db.entities.UserGuildInformation;
import de.jan.DiscordStatisticsBot.core.db.services.UserGuildInformationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class UserGuildInformationController {

    private final Logger LOGGER = LoggerFactory.getLogger(UserGuildInformationController.class);

    @Autowired
    private UserGuildInformationService userGuildInformationService;

    @GetMapping("/api/userInfo")
    public List<UserGuildInformation> getAllUserInfo(@RequestParam(name = "page", defaultValue = "0") String page, @RequestParam(name = "size", defaultValue = "15") String size) {
        int convPage = Integer.parseInt(page);
        int convSize = Integer.parseInt(size);

        LOGGER.trace(String.format("Get %s user information from page %s", size, page));

        Pageable pageable = Pageable.ofSize(convSize);

        while(convPage != pageable.getPageNumber()) {
            pageable = pageable.next();
        }

        return userGuildInformationService.getAllPageable(pageable).getContent();
    }

    @GetMapping("/api/userInfo/{guildId}/{userId}")
    public UserGuildInformation getUserInfoByUserAndGuild(@PathVariable String guildId, @PathVariable String userId) {
        LOGGER.trace(String.format("Get user information for guild %s and user %s", guildId, userId));

        return userGuildInformationService.getById(Long.parseLong(userId), Long.parseLong(guildId));
    }

    @GetMapping("/api/userInfo/user/{userId}")
    public Set<UserGuildInformation> getAllUserInfoByUser(@PathVariable String userId) {
        LOGGER.trace(String.format("Get user information for user %s", userId));

        return userGuildInformationService.getAllByUserId(Long.parseLong(userId));
    }

    @GetMapping("/api/userInfo/guild/{guildId}")
    public Set<UserGuildInformation> getAllUserInfoByGuild(@PathVariable String guildId) {
        LOGGER.trace(String.format("Get guild information for guild %s", guildId));

        return userGuildInformationService.getAllByGuildId(Long.parseLong(guildId));
    }
}
