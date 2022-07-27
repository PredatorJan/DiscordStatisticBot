package de.jan.DiscordStatisticsBot.core.db.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BasicService<ENTITY> {

    ENTITY save(ENTITY object);

    void delete(ENTITY object);

    ENTITY getById(long id);

    Page<ENTITY> getAllPageable(Pageable pageable);

    long getCount();
}
