package de.jan.DiscordStatisticsBot.core.db.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "guild_id")
    private Guild guild;

    public Message() {
        this(null, null, null);
    }

    public Message(Guild guild, Timestamp timestamp, User user) {
        this.guild = guild;
        this.timestamp = timestamp;
        this.user = user;
    }

    @Override
    public String toString() {
        return String.format("Message [Id: %d, Timestamp: %s, User: %s]", id, timestamp, user);
    }

    public Long getId() {
        return id;
    }

    public Guild getGuild() {
        return guild;
    }

    public void setGuild(Guild guild) {
        this.guild = guild;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
