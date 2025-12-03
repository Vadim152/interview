package org.example.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestConfig {
    private static final String DEFAULT_CONFIG = "/config.properties";
    private static final String ENV_CONFIG_PATTERN = "/config.%s.properties";

    private static final Properties properties = new Properties();

    static {
        loadDefaults();
        loadEnvironmentSpecific();
        overrideWithSystemProperties();
    }

    private TestConfig() {
    }

    private static void loadDefaults() {
        try (InputStream input = TestConfig.class.getResourceAsStream(DEFAULT_CONFIG)) {
            if (input != null) {
                properties.load(input);
            }
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load default config", e);
        }
    }

    private static void loadEnvironmentSpecific() {
        String env = System.getProperty("env");
        if (env == null || env.isBlank()) {
            return;
        }

        String resourcePath = ENV_CONFIG_PATTERN.formatted(env);
        try (InputStream input = TestConfig.class.getResourceAsStream(resourcePath)) {
            if (input != null) {
                properties.load(input);
            }
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load config for env: " + env, e);
        }
    }

    private static void overrideWithSystemProperties() {
        System.getProperties().forEach((key, value) -> properties.put(key, value));
    }

    public static String getBaseUrl() {
        return properties.getProperty("baseUrl", "https://www.saucedemo.com");
    }

    public static String getBrowser() {
        return properties.getProperty("browser", "chrome");
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(properties.getProperty("headless", "true"));
    }

    public static long getImplicitTimeout() {
        return Long.parseLong(properties.getProperty("implicitTimeout", "5"));
    }

    public static long getPageLoadTimeout() {
        return Long.parseLong(properties.getProperty("pageLoadTimeout", "30"));
    }
}
