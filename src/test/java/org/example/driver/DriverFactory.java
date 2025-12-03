package org.example.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.example.config.TestConfig;

public final class DriverFactory {

    private DriverFactory() {
    }

    public static WebDriver createDriver(TestConfig config) {
        String browser = config.getBrowser().toLowerCase();

        return switch (browser) {
            case "firefox" -> createFirefox(config.isHeadless());
            case "chrome" -> createChrome(config.isHeadless());
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };
    }

    private static WebDriver createChrome(boolean headless) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(headless);
        options.addArguments("--disable-gpu", "--no-sandbox", "--window-size=1920,1080");
        return new ChromeDriver(options);
    }

    private static WebDriver createFirefox(boolean headless) {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(headless);
        options.addArguments("--width=1920", "--height=1080");
        return new FirefoxDriver(options);
    }
}
