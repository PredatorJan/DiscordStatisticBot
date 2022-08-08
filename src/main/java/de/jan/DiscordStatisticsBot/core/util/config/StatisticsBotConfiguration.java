package de.jan.DiscordStatisticsBot.core.util.config;

import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Service
public class StatisticsBotConfiguration implements IStatisticsBotConfiguration{

    private static String configPath = "./config/config.properties";

    private static Properties properties = new Properties();

    static {
        try {
            properties.load(new FileInputStream(configPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
