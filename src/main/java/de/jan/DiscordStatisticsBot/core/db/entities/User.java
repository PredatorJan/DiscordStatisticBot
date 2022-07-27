package de.jan.DiscordStatisticsBot.core.db.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String discordid;

    private String name;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user")
    private Set<Message> message;

    @OneToMany(mappedBy = "guild")
    private Set<UserGuildInformation> userGuildInformation;

    public User() {
        this(null, null, null);
    }

    public User(String discordid, String name, Role role) {
        this.discordid = discordid;
        this.name = name;
        this.role = role;
    }

    @Override
    public String toString() {
        return String.format("User [Id: %d, DiscordId: %s, Name: %s, Role: %s]", id, discordid, name, role);
    }

    public Long getId() {
        return id;
    }

    public String getDiscordid() {
        return discordid;
    }

    public void setDiscordid(String discordid) {
        this.discordid = discordid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
