package de.jan.DiscordStatisticsBot.core.bot;

import de.jan.DiscordStatisticsBot.core.bot.listener.GuildListener;
import de.jan.DiscordStatisticsBot.core.bot.listener.MessageListener;
import de.jan.DiscordStatisticsBot.core.bot.listener.SlashCommandListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.security.auth.login.LoginException;

@Component
public class StatisticsBot {

    private final String token = "OTg0MjAxNzc1ODQ0MzAyOTE4.GJoW-x.GI2QLiuO9xioQuNnmPgvq3TJ4xZRh08XgQoEmU";

    private SlashCommandListener slashCommandListener;
    private MessageListener messageListener;
    private GuildListener guildListener;

    @Autowired
    public StatisticsBot(MessageListener messageListener, GuildListener guildListener, SlashCommandListener slashCommandListener) {
        this.messageListener = messageListener;
        this.guildListener = guildListener;
        this.slashCommandListener = slashCommandListener;
    }

    public void startBot() throws LoginException {
        JDABuilder jdaBuilder = JDABuilder.createDefault(token);
        jdaBuilder.addEventListeners(
                slashCommandListener,
                messageListener,
                guildListener);

        JDA jda = jdaBuilder.build();

        // Slash Commands
        jda.upsertCommand("ping", "Ping Statistics Bot").queue();
    }
}
