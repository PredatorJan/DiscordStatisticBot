package de.jan.DiscordStatisticsBot.core.db.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "guilds")
public class Guild {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String discordId;

    private String name;

    private int currentMembers;

    private int activeMembers;

    private int inactiveMembers;

    @OneToMany(mappedBy = "guild")
    private Set<Message> messsages;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<UserGuildInformation> userGuildInformation;

    public Guild() {
        this(null, null, 0, 0, 0);
    }

    public Guild(String discordId, String name, int currentMembers, int activeMembers, int inactiveMembers) {
        this.discordId = discordId;
        this.name = name;
        this.currentMembers = currentMembers;
        this.activeMembers = activeMembers;
        this.inactiveMembers = inactiveMembers;
    }

    @Override
    public String toString() {
        return String.format("Guild [ID: %d, DiscordId: %s, Name: %s, CurrentMembers: %d, ActiveMembers: %d, InactiveMembers: %d]", id, discordId, name, currentMembers, activeMembers, inactiveMembers);
    }

    public long getId() {
        return id;
    }

    public String getDiscordId() {
        return discordId;
    }

    public void setDiscordId(String discordId) {
        this.discordId = discordId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentMembers() {
        return currentMembers;
    }

    public void setCurrentMembers(int currentMembers) {
        this.currentMembers = currentMembers;
    }

    public int getActiveMembers() {
        return activeMembers;
    }

    public void setActiveMembers(int activeMembers) {
        this.activeMembers = activeMembers;
    }

    public int getInactiveMembers() {
        return inactiveMembers;
    }

    public void setInactiveMembers(int inactiveMembers) {
        this.inactiveMembers = inactiveMembers;
    }
}
