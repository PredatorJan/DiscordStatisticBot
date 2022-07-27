package de.jan.DiscordStatisticsBot.core.bot.listener;

import de.jan.DiscordStatisticsBot.core.db.entities.Guild;
import de.jan.DiscordStatisticsBot.core.db.services.GuildService;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateNameEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GuildListener extends ListenerAdapter {

    private Logger LOGGER = LoggerFactory.getLogger(GuildListener.class);

    @Autowired
    private GuildService guildService;

    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        LOGGER.info("Joined Guild - " + event.getGuild().getName());

        createNewGuild(event.getGuild());
    }

    @Override
    public void onGuildLeave(@NotNull GuildLeaveEvent event) {
        LOGGER.info("Guild Leaved - " + event.getGuild().getName());
    }

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        LOGGER.info("Guild Ready - " + event.getGuild().getName());

        createOrUpdateNewGuild(event.getGuild());
    }

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        LOGGER.info(String.format("User %s joined guild %s", event.getUser().getName(), event.getGuild().getName()));

        Guild guild = guildService.getGuild(event.getGuild().getIdLong());
        updateGuild(guild, event.getGuild());
    }

    @Override
    public void onGuildUpdateName(@NotNull GuildUpdateNameEvent event) {
        LOGGER.info(String.format("Guild name changed from '%s' to '%s'", event.getOldName(), event.getNewName()));

        Guild guild = guildService.getGuild(event.getGuild().getIdLong());
        updateGuild(guild, event.getGuild());
    }

    private void createOrUpdateNewGuild(net.dv8tion.jda.api.entities.Guild guildJda) {
        Guild guild = guildService.getGuild(guildJda.getIdLong());
        if (guild == null) {
            createNewGuild(guildJda);
        } else {
            updateGuild(guild, guildJda);
        }
    }

    private void createNewGuild(net.dv8tion.jda.api.entities.Guild guildJda) {
        String discordId = String.valueOf(guildJda.getIdLong());
        int currentUsers = guildJda.getMemberCount();

        Guild newGuild = new Guild(discordId, guildJda.getName(), currentUsers, 0, 0);

        guildService.save(newGuild);
    }

    private void updateGuild(Guild guild, net.dv8tion.jda.api.entities.Guild guildJda) {
        guild.setName(guildJda.getName());
        guild.setCurrentMembers(guildJda.getMemberCount());

        guildService.save(guild);
    }
}
