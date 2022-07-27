package de.jan.DiscordStatisticsBot.core.tasks;

import de.jan.DiscordStatisticsBot.core.db.entities.Guild;
import de.jan.DiscordStatisticsBot.core.db.entities.Message;
import de.jan.DiscordStatisticsBot.core.db.entities.User;
import de.jan.DiscordStatisticsBot.core.db.entities.UserGuildInformation;
import de.jan.DiscordStatisticsBot.core.db.services.GuildService;
import de.jan.DiscordStatisticsBot.core.db.services.MessageService;
import de.jan.DiscordStatisticsBot.core.db.services.UserGuildInformationService;
import de.jan.DiscordStatisticsBot.core.db.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
@Transactional
public class CalculateMessageRateTask {

    private final Logger LOGGER = LoggerFactory.getLogger(CalculateMessageRateTask.class);

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private GuildService guildService;

    @Autowired
    private UserGuildInformationService userGuildInformationService;

    private final int PAGE_SIZE = 5;

    @Scheduled(fixedRate = 30, initialDelay = 1, timeUnit = TimeUnit.MINUTES)
    public void run() {
        LOGGER.info("Execute " + getClass().getSimpleName());

        Page<UserGuildInformation> userGuildInformationPage = null;

        do {
            if(userGuildInformationPage == null) {
                userGuildInformationPage = userGuildInformationService.getAllPageable(Pageable.ofSize(PAGE_SIZE));
            } else {
                userGuildInformationPage = userGuildInformationService.getAllPageable(userGuildInformationPage.nextPageable());
            }

            userGuildInformationPage.forEach(userGuildInformation -> calculateRates(userGuildInformation));
        } while(!userGuildInformationPage.isLast());
    }

    private void calculateRates(UserGuildInformation userGuildInformation) {
        User user = userGuildInformation.getUser();
        Guild guild = userGuildInformation.getGuild();

        LOGGER.debug("Calculate message rates for user: " + user + " and for guild: " + guild);

        Timestamp startTimestamp = Timestamp.valueOf(LocalDateTime.now().minusMonths(1).withHour(0).withMinute(0).withSecond(0).withNano(0));
        Timestamp endTimestamp = Timestamp.valueOf(LocalDateTime.now().withHour(23).withMinute(59).withSecond(59).withNano(0));
        LOGGER.trace(String.format("StartTimestamp: %s", startTimestamp));
        LOGGER.trace(String.format("EndTimestamp: %s", endTimestamp));

        Set<Message> messagesBetweenTimestamp = messageService.getMessagesBetweenTimestamp(user, guild, startTimestamp, endTimestamp);

        long daysBetween = Duration.between(startTimestamp.toLocalDateTime(), endTimestamp.toLocalDateTime()).toDays();

        int rateDay = calculateRatePerDay(messagesBetweenTimestamp, (int) daysBetween);
        int rateWeek = calculateRatePerWeek(messagesBetweenTimestamp);
        int rateMonth = calculateRatePerMonth(messagesBetweenTimestamp);

        userGuildInformation.setRateDay(rateDay);
        userGuildInformation.setRateWeek(rateWeek);
        userGuildInformation.setRateMonth(rateMonth);

        userGuildInformationService.save(userGuildInformation);
    }

    private int calculateRatePerDay(Set<Message> messages, int days) {
        double rate = calculateRate(messages, days);

        LOGGER.trace("DayRate: " + rate);
        return (int) rate;
    }

    private int calculateRatePerWeek(Set<Message> messages) {
        double rate = calculateRate(messages, 4);

        LOGGER.trace("WeekRate: " + rate);

        return (int) rate;
    }

    private int calculateRatePerMonth(Set<Message> messages) {
        double rate = calculateRate(messages, 1);

        LOGGER.trace("MonthRate: " + rate);

        return (int) rate;
    }

    private double calculateRate (Set<Message> messages, double timeUnits) {
        double messageCount = messages.size();
        double units = timeUnits;

        return messageCount / units;
    }
}
