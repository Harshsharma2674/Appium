package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    //Returns the value for a key from config.properties
    //Example: ConfigReader.get("device.name") --> "emulator-5554"
    public static String get(String key) {

        Properties properties = new Properties();

        try {
            FileInputStream fis =
                    new FileInputStream("src/test/resources/config.properties");

            properties.load(fis);
            fis.close();

        } catch (Exception e) {
            throw new RuntimeException("Could not read config.properties", e);
        }

        return properties.getProperty(key);
    }
}
