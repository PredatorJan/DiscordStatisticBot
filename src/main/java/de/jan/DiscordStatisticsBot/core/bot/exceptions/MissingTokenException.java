package de.jan.DiscordStatisticsBot.core.bot.exceptions;

public class MissingTokenException extends Exception {

    public MissingTokenException(String message) {
        super(message);
    }

    public MissingTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}
