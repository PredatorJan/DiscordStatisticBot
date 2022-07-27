package de.jan.DiscordStatisticsBot.controller;

import de.jan.DiscordStatisticsBot.core.db.entities.User;
import de.jan.DiscordStatisticsBot.core.db.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/api/users")
    public List<User> getAllUsers(@RequestParam(name = "page", defaultValue = "0") String page, @RequestParam(name = "size", defaultValue = "15") String size) {

        int convPage = Integer.parseInt(page);
        int convSize = Integer.parseInt(size);

        LOGGER.trace(String.format("Get %s users from page %s", size, page));

        Pageable pageable = Pageable.ofSize(convSize);

        while(convPage != pageable.getPageNumber()) {
            pageable = pageable.next();
        }

        return userService.getAllPageable(pageable).getContent();
    }

    @GetMapping("/api/users/{id}")
    public User getUserById(@PathVariable String id) {
        LOGGER.trace(String.format("Get user with id %s", id));

        return userService.getById(Long.parseLong(id));
    }

    @GetMapping("/api/users/count")
    public long getUserCount() {
        return userService.getCount();
    }
}
