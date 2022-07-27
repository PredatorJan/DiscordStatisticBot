package de.jan.DiscordStatisticsBot.core.tasks;

import de.jan.DiscordStatisticsBot.core.db.services.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Component
public class DeleteMessageTask {

    private final Logger LOGGER = LoggerFactory.getLogger(DeleteMessageTask.class);

    @Autowired
    private MessageService messageService;

    @Scheduled(fixedRate = 24, initialDelay = 1, timeUnit = TimeUnit.HOURS)
    public void run() {
        LOGGER.info("Execute " + getClass().getSimpleName());

        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now().minusMonths(1).withHour(0).withMinute(0).withSecond(0).withNano(0));

        long deletedCount = messageService.deleteMessagesOlderThanTimestamp(timestamp);

        LOGGER.info("Deleted " + deletedCount + " messages");
    }
}
