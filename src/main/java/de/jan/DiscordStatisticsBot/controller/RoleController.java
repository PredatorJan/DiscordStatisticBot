package de.jan.DiscordStatisticsBot.controller;

import de.jan.DiscordStatisticsBot.core.db.entities.Role;
import de.jan.DiscordStatisticsBot.core.db.entities.User;
import de.jan.DiscordStatisticsBot.core.db.services.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleController {

    private final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @GetMapping("/roles")
    public List<Role> getAllRoles(@RequestParam(name = "page", defaultValue = "0") String page, @RequestParam(name = "size", defaultValue = "15") String size) {
        int convPage = Integer.parseInt(page);
        int convSize = Integer.parseInt(size);

        LOGGER.trace(String.format("Get %s roles from page %s", size, page));

        Pageable pageable = Pageable.ofSize(convSize);

        while(convPage != pageable.getPageNumber()) {
            pageable = pageable.next();
        }

        return roleService.getAllPageable(pageable).getContent();
    }

    @GetMapping("/roles/{id}")
    public Role getRoleById(@PathVariable String id) {
        LOGGER.trace(String.format("Get role with id %s", id));

        return roleService.getById(Long.parseLong(id));
    }
}
