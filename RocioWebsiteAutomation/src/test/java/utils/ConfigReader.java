package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    Properties properties;

    public ConfigReader() {

        properties = new Properties();

        try {

            FileInputStream file =
                    new FileInputStream(
                    "src/test/resources/config.properties");

            properties.load(file);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public String getBaseUrl() {

        return properties.getProperty("baseUrl");
    }

    public String getBrowser() {

        return properties.getProperty("browser");
    }
    
    public String getDbUrl() {

        return properties.getProperty("dbUrl");
    }

    public String getDbUser() {

        return properties.getProperty("dbUser");
    }

    public String getDbPassword() {

        return properties.getProperty("dbPassword");
    }

    public String getApiBaseUrl() {

        return properties.getProperty("apiBaseUrl");
    }
    
}