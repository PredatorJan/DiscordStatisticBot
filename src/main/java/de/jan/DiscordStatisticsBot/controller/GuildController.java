package de.jan.DiscordStatisticsBot.controller;

import de.jan.DiscordStatisticsBot.core.db.entities.Guild;
import de.jan.DiscordStatisticsBot.core.db.entities.UserGuildInformation;
import de.jan.DiscordStatisticsBot.core.db.services.GuildService;
import de.jan.DiscordStatisticsBot.core.db.services.UserGuildInformationService;
import de.jan.DiscordStatisticsBot.core.db.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class GuildController {

    private final Logger LOGGER = LoggerFactory.getLogger(GuildController.class);

    @Autowired
    private GuildService guildService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserGuildInformationService userGuildInformationService;

    @GetMapping("/api/guilds")
    public List<Guild> getAllGuilds(@RequestParam(name = "page", defaultValue = "0") String page, @RequestParam(name = "size", defaultValue = "15") String size) {
        int convPage = Integer.parseInt(page);
        int convSize = Integer.parseInt(size);

        LOGGER.trace(String.format("Get %s guilds from page %s", size, page));

        Pageable pageable = Pageable.ofSize(convSize);

        while(convPage != pageable.getPageNumber()) {
            pageable = pageable.next();
        }

        return guildService.getAllPageable(pageable).getContent();
    }

    @GetMapping("/api/guilds/{id}")
    public Guild getGuildById(@PathVariable String id) {
        LOGGER.trace(String.format("Get role with id %s", id));

        return guildService.getById(Long.parseLong(id));
    }

    @GetMapping("/api/guilds/name/{name}")
    public Set<Guild> getGuildByName(@PathVariable String name) {
        LOGGER.trace(String.format("Get guild that contains %s in name", name));

        return guildService.getGuildByName(name);
    }

    @GetMapping("/api/guilds/count")
    public long getGuildCount() {
        return guildService.getCount();
    }

    @GetMapping("/api/guilds/count/{userId}")
    public long getGuildCountByUser(@PathVariable String userId) {
        LOGGER.trace(String.format("Get guild count for user %s", userId));

        long parsedUserId = Long.parseLong(userId);

        Set<UserGuildInformation> userGuildInformations = userGuildInformationService.getAllByUserId(parsedUserId);

        return userGuildInformations.size();
    }

    @GetMapping("/api/guilds/user/{id}")
    public List<Guild> getGuildsByUser(@PathVariable String id, @RequestParam(name = "page", defaultValue = "0") String page, @RequestParam(name = "size", defaultValue = "15") String size) {
        int convPage = Integer.parseInt(page);
        int convSize = Integer.parseInt(size);

        LOGGER.trace(String.format("Get %s guilds from page %s for userId %s", size, page, id));

        Pageable pageable = Pageable.ofSize(convSize);

        while(convPage != pageable.getPageNumber()) {
            pageable = pageable.next();
        }

        List<UserGuildInformation> userGuildInformations = userGuildInformationService.getAllByUserIdPageable(Long.parseLong(id), pageable);

        List<Guild> guilds = new ArrayList<>();
        userGuildInformations.forEach(userGuildInformation -> guilds.add(userGuildInformation.getGuild()));

        return guilds;
    }

}
