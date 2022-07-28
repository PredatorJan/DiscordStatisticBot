package de.jan.DiscordStatisticsBot;

import de.jan.DiscordStatisticsBot.core.bot.StatisticsBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.security.auth.login.LoginException;

@SpringBootApplication
@EnableScheduling
public class DiscordStatisticsBotApplication {

	private final Logger LOGGER = LoggerFactory.getLogger(DiscordStatisticsBotApplication.class);

	private static String DISCORD_TOKEN;

	@Autowired
	private StatisticsBot statisticsBot;

	public static void main(String[] args) {
		DISCORD_TOKEN = args[0];

		SpringApplication.run(DiscordStatisticsBotApplication.class, args);
	}

	@Bean
	public void startBot() {
		try {
			statisticsBot.startBot(DISCORD_TOKEN);
		} catch (LoginException e) {
			LOGGER.error("Invalid token while starting discord bot", e);
		}
	}
}
