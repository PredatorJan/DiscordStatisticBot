package de.jan.DiscordStatisticsBot.core.db.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "role")
    private Set<User> user;

    public Role() {
        this(null, null, null);
    }

    public Role(String name, String description, Set<User> user) {
        this.name = name;
        this.description = description;
        this.user = user;
    }

    @Override
    public String toString() {
        return String.format("Role [ID: %d, Name: %s, Description: %s]", id, name, description);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
