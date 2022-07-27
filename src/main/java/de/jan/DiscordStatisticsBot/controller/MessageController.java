package de.jan.DiscordStatisticsBot.controller;

import de.jan.DiscordStatisticsBot.core.db.entities.Guild;
import de.jan.DiscordStatisticsBot.core.db.entities.Message;
import de.jan.DiscordStatisticsBot.core.db.entities.User;
import de.jan.DiscordStatisticsBot.core.db.services.GuildService;
import de.jan.DiscordStatisticsBot.core.db.services.MessageService;
import de.jan.DiscordStatisticsBot.core.db.services.UserService;
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
public class MessageController {

    private Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private GuildService guildService;

    @GetMapping("/api/messages")
    public List<Message> getAllMessages(@RequestParam(name = "page", defaultValue = "0") String page, @RequestParam(name = "size", defaultValue = "15") String size) {
        int convPage = Integer.parseInt(page);
        int convSize = Integer.parseInt(size);

        LOGGER.trace(String.format("Get %s message from page %s", size, page));

        Pageable pageable = Pageable.ofSize(convSize);

        while(convPage != pageable.getPageNumber()) {
            pageable = pageable.next();
        }

        return messageService.getAllPageable(pageable).getContent();
    }

    @GetMapping("/api/messages/{id}")
    public Message getMessagesById(@PathVariable String id) {
        LOGGER.trace(String.format("Get message with id %s", id));

        return messageService.getById(Long.parseLong(id));
    }

    @GetMapping("/api/messages/user/discordId/{id}")
    public Set<Message> getAllMessagesForUserByDiscordId(@PathVariable String id) {
        LOGGER.trace(String.format("Get messages with discordId %s", id));

        User user = userService.getUser(Long.parseLong(id));

        return messageService.getMessagesByUser(user);
    }

    @GetMapping("/api/messages/user/{id}")
    public Set<Message> getAllMessagesForUserById(@PathVariable String id) {
        LOGGER.trace(String.format("Get messages with id %s", id));

        User user = userService.getById(Long.parseLong(id));

        return messageService.getMessagesByUser(user);
    }

    @GetMapping("/api/messages/{userId}/{guildId}")
    public Set<Message> getAllMessagesForUserAndGuild(@PathVariable String userId, @PathVariable String guildId) {
        LOGGER.trace(String.format("Get messages with userId %s and guildId %s", userId, guildId));

        User user = userService.getById(Long.parseLong(userId));
        Guild guild = guildService.getById(Long.parseLong(guildId));

        return messageService.getMessagesByUserAndGuild(user, guild);
    }

}
