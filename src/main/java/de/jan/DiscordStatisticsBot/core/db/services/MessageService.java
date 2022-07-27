package de.jan.DiscordStatisticsBot.core.db.services;

import de.jan.DiscordStatisticsBot.core.db.entities.Guild;
import de.jan.DiscordStatisticsBot.core.db.entities.Message;
import de.jan.DiscordStatisticsBot.core.db.entities.User;
import de.jan.DiscordStatisticsBot.core.db.repositories.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Set;

@Service
public class MessageService implements BasicService<Message> {

    private Logger LOGGER = LoggerFactory.getLogger(MessageService.class);

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Message save(Message message) {
        LOGGER.trace("Save message: " + message);
        return messageRepository.save(message);
    }

    @Override
    public void delete(Message message) {
        LOGGER.trace("Delete messsage: " + message);
        messageRepository.delete(message);
    }

    @Override
    public Message getById(long id) {
        LOGGER.trace(String.format("Get message by id %d", id));
        return messageRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Message> getAllPageable(Pageable pageable) {
        return messageRepository.findAll(pageable);
    }

    @Override
    public long getCount() {
        return messageRepository.count();
    }

    public Set<Message> getMessagesBetweenTimestamp(User user, Guild guild, Timestamp startTimestamp, Timestamp endTimestamp) {
        return messageRepository.findByUserAndGuildAndTimestampBetween(user, guild, startTimestamp, endTimestamp);
    }

    public long deleteMessagesOlderThanTimestamp(Timestamp timestamp) {
        return messageRepository.deleteByTimestampLessThan(timestamp);
    }

    public Set<Message> getMessagesByUser(User user) {
        return messageRepository.findByUser(user);
    }

    public Set<Message> getMessagesByGuild(Guild guild) {
        return messageRepository.findByGuild(guild);
    }

    public Set<Message> getMessagesByUserAndGuild(User user, Guild guild) {
        return messageRepository.findByUserAndGuild(user, guild);
    }
}
