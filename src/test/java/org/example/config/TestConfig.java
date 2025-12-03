package org.example.config;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Objects;
import java.util.Properties;

public class TestConfig {

    private final Properties properties;

    public TestConfig() {
        this.properties = loadProperties();
    }

    public String getBaseUrl() {
        return Objects.requireNonNull(properties.getProperty("baseUrl"), "baseUrl is not set");
    }

    public String getBrowser() {
        return properties.getProperty("browser", "chrome");
    }

    public boolean isHeadless() {
        return Boolean.parseBoolean(properties.getProperty("headless", "false"));
    }

    public Duration getImplicitTimeout() {
        String implicitTimeoutSeconds = properties.getProperty("implicitTimeout", "5");
        return Duration.ofSeconds(Long.parseLong(implicitTimeoutSeconds));
    }

    public Duration getPageLoadTimeout() {
        String pageLoadTimeoutSeconds = properties.getProperty("pageLoadTimeout", "30");
        return Duration.ofSeconds(Long.parseLong(pageLoadTimeoutSeconds));
    }

    private Properties loadProperties() {
        Properties properties = new Properties();
        String env = System.getProperty("env", "").trim();
        String fileName = env.isEmpty() ? "config.properties" : String.format("config.%s.properties", env);

        try (InputStream stream = TestConfig.class.getClassLoader().getResourceAsStream(fileName)) {
            if (stream != null) {
                properties.load(stream);
            } else {
                throw new IllegalStateException("Cannot find properties file: " + fileName);
            }
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load properties: " + fileName, e);
        }

        System.getProperties()
                .forEach((key, value) -> properties.setProperty(String.valueOf(key), String.valueOf(value)));

        return properties;
    }
}
