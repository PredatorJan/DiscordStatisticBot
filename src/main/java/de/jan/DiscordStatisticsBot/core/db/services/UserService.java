package de.jan.DiscordStatisticsBot.core.db.services;

import de.jan.DiscordStatisticsBot.core.db.entities.User;
import de.jan.DiscordStatisticsBot.core.db.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService implements BasicService<User> {

    private Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        LOGGER.trace("Save user: " + user);
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        LOGGER.trace("Delete user: " + user);
        userRepository.delete(user);
    }

    @Override
    public User getById(long id) {
        LOGGER.trace(String.format("Get user by id %d", id));
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public Page<User> getAllPageable(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public long getCount() {
        return userRepository.count();
    }

    public User getUser(long discordId) {
        LOGGER.trace(String.format("Get user by discordid %d", discordId));
        return userRepository.findByDiscordid(String.valueOf(discordId));
    }
}
