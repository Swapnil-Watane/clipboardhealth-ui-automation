package Common;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    protected static final Logger logger= Logger.getLogger(ConfigManager.class);
    private static final String DEFAULT_CONFIG_PATH = "src/main/resources/config/test_config.properties";
    private static final Properties testProperties = new Properties();

    public ConfigManager() {
        this.readProperties();
    }

    private void readProperties() {
        try {
            InputStream propertyReader = new FileInputStream(ConfigManager.DEFAULT_CONFIG_PATH);
            testProperties.load(propertyReader);
        } catch (IOException e) {
            logger.error("Error while reading configuration: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public String getProperty(String propertyName) {
        return testProperties.getProperty(propertyName);
    }
}
