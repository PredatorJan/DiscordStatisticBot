package de.jan.DiscordStatisticsBot.core.db.entities.compositeIds;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserGuildInformationPK implements Serializable {

    private long guildId;

    private long userId;

    public UserGuildInformationPK() {

    }

    public UserGuildInformationPK(long guildId, long userId) {
        this.guildId = guildId;
        this.userId = userId;
    }

    public long getGuildId() {
        return guildId;
    }

    public void setGuildId(long guildId) {
        this.guildId = guildId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
