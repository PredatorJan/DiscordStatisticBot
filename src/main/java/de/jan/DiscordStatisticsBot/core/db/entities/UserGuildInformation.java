package de.jan.DiscordStatisticsBot.core.db.entities;

import de.jan.DiscordStatisticsBot.core.db.entities.compositeIds.UserGuildInformationPK;

import javax.persistence.*;

@Entity
public class UserGuildInformation {

    @EmbeddedId
    private UserGuildInformationPK userGuildInformationPK = new UserGuildInformationPK();

    @ManyToOne
    @JoinColumn(name = "guild_id")
    @MapsId("guildId")
    private Guild guild;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @MapsId("userId")
    private User user;

    private int rateDay;

    private int rateWeek;

    private int rateMonth;

    @Override
    public String toString() {
        return String.format("UserGuildInformation [Guild_ID: %d, User_ID: %d, RateDay: %d, RateWeek: %d, RateMonth: %d]", guild.getId(), user.getId(), rateDay, rateWeek, rateMonth);
    }

    public Guild getGuild() {
        return guild;
    }

    public void setGuild(Guild guild) {
        this.guild = guild;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRateDay() {
        return rateDay;
    }

    public void setRateDay(int rateDay) {
        this.rateDay = rateDay;
    }

    public int getRateWeek() {
        return rateWeek;
    }

    public void setRateWeek(int rateWeek) {
        this.rateWeek = rateWeek;
    }

    public int getRateMonth() {
        return rateMonth;
    }

    public void setRateMonth(int rateMonth) {
        this.rateMonth = rateMonth;
    }
}
