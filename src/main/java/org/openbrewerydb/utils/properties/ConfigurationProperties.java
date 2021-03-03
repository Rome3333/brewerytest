package org.openbrewerydb.utils.properties;

public final class ConfigurationProperties {

    private static final String BASE_URI = "base.uri";

    public static String getBaseUri() {
        return getFileProperty(BASE_URI);
    }

    private static String getFileProperty(String key) {
        return PropertiesHolder.getInstance().getProperty(key);
    }

}
