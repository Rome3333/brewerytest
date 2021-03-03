package org.openbrewerydb.utils.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

public class PropertiesHolder {

    private static PropertiesHolder instance;

    private final Properties properties;

    private PropertiesHolder() {
        properties = new Properties();
        load();
    }

    public static PropertiesHolder getInstance() {
        return Optional.ofNullable(instance).orElseGet(() -> instance = new PropertiesHolder());
    }

    public String getProperty(String key) {
        return Optional.ofNullable(properties.getProperty(key)).orElseThrow(() -> new IllegalArgumentException("Can't find property" + key));
    }

    private void load() {
        InputStream in = PropertiesHolder.class.getClassLoader().getResourceAsStream(ConfigurationConstants.PROPERTY_FILE);
        try {
            properties.load(in);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load properties file. " + e);
        }

    }

}
