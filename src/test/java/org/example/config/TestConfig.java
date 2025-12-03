package org.example.config;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Objects;
import java.util.Properties;

/**
 * Loads test configuration from property files and system properties.
 */
public final class TestConfig {

    private static final String ENV = System.getProperty("env", "");
    private static final Properties PROPERTIES = loadProperties();

    private TestConfig() {
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        String envSuffix = ENV.isBlank() ? "" : "." + ENV;
        String resourceName = String.format("config%s.properties", envSuffix);

        try (InputStream stream = TestConfig.class.getClassLoader().getResourceAsStream(resourceName)) {
            if (stream != null) {
                properties.load(stream);
            } else {
                throw new IllegalStateException("Cannot find properties file: " + resourceName);
            }
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load properties: " + resourceName, e);
        }

        // System properties override file properties
        System.getProperties().forEach((key, value) -> properties.setProperty(String.valueOf(key), String.valueOf(value)));
        return properties;
    }

    public static String getBaseUrl() {
        return Objects.requireNonNull(PROPERTIES.getProperty("baseUrl"), "baseUrl is not set");
    }

    public static String getBrowser() {
        return PROPERTIES.getProperty("browser", "chrome");
    }

    public static Duration getTimeout() {
        String timeoutSeconds = PROPERTIES.getProperty("timeout.seconds", "10");
        return Duration.ofSeconds(Long.parseLong(timeoutSeconds));
    }
}
