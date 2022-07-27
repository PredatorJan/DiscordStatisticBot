package de.jan.DiscordStatisticsBot.core.bot.listener;

import de.jan.DiscordStatisticsBot.core.db.entities.*;
import de.jan.DiscordStatisticsBot.core.db.services.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.Set;

@Component
@Transactional
public class MessageListener extends ListenerAdapter {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private GuildService guildService;

    @Autowired
    private UserGuildInformationService userGuildInformationService;

    private final Logger logger = LoggerFactory.getLogger(MessageListener.class);

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getAuthor().isBot())
            return;

        logger.debug("Received message: " + event.getMessage().getAuthor().getName() + " - " + event.getMessage().getTimeCreated());

        OffsetDateTime timeCreated = event.getMessage().getTimeCreated();
        long millis = timeCreated.toEpochSecond() * 1000;

        Guild guild = guildService.getGuild(event.getGuild().getIdLong());
        User user = getOrCreateUser(event.getAuthor(), guild);

        Message message = new Message(guild, new Timestamp(millis), user);
        messageService.save(message);
    }

    private User getOrCreateUser(net.dv8tion.jda.api.entities.User author, Guild guild) {
        User user = userService.getUser(author.getIdLong());

        if (user == null) {
            Role role = roleService.getById(1);

            user = new User(String.valueOf(author.getIdLong()), author.getName(), role);
            userService.save(user);

            addUserToGuild(user, guild);
        } else if(!isUserAssignedToGuild(user, guild)) {
            addUserToGuild(user, guild);
        }

        return user;
    }

    private boolean isUserAssignedToGuild(User user, Guild guild) {
        UserGuildInformation userGuildInformation = userGuildInformationService.getById(user.getId(), guild.getId());

        return userGuildInformation != null;
    }

    private void addUserToGuild(User user, Guild guild) {
        UserGuildInformation newUserGuildInformationEntry = new UserGuildInformation();
        newUserGuildInformationEntry.setUser(user);
        newUserGuildInformationEntry.setGuild(guild);
        newUserGuildInformationEntry.setRateDay(0);
        newUserGuildInformationEntry.setRateWeek(0);
        newUserGuildInformationEntry.setRateMonth(0);

        userGuildInformationService.save(newUserGuildInformationEntry);
    }
}
