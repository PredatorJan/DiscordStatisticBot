package de.jan.DiscordStatisticsBot.core.db.services;

import de.jan.DiscordStatisticsBot.core.db.entities.Role;
import de.jan.DiscordStatisticsBot.core.db.repositories.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements BasicService<Role>{

    private Logger LOGGER = LoggerFactory.getLogger(RoleService.class);

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        LOGGER.trace("Save role: " + role);
        return roleRepository.save(role);
    }

    @Override
    public void delete(Role role) {
        LOGGER.trace("Delete role: " + role);
        roleRepository.delete(role);
    }

    @Override
    public Role getById(long id) {
        LOGGER.trace(String.format("Get role by id %d", id));
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Role> getAllPageable(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }

    @Override
    public long getCount() {
        return roleRepository.count();
    }
}
