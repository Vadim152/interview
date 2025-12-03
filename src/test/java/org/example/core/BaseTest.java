package org.example.core;

import io.qameta.allure.Allure;
import org.example.config.TestConfig;
import org.example.driver.DriverFactory;
import org.example.driver.DriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.nio.charset.StandardCharsets;

public abstract class BaseTest {

    @BeforeEach
    public void setUp() {
        WebDriver driver = DriverFactory.createDriver();
        DriverManager.setDriver(driver);
        driver.get(TestConfig.getBaseUrl());
    }

    @AfterEach
    public void tearDown() {
        attachPageSource();
        attachScreenshot();
        DriverManager.unload();
    }

    private void attachScreenshot() {
        WebDriver driver = DriverManager.getDriver();
        if (driver instanceof TakesScreenshot screenshot) {
            byte[] bytes = screenshot.getScreenshotAs(OutputType.BYTES);
            Allure.getLifecycle().addAttachment("Скриншот", "image/png", "png", bytes);
        }
    }

    private void attachPageSource() {
        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            String source = driver.getPageSource();
            Allure.getLifecycle().addAttachment("Исходный код страницы", "text/html", "html", source.getBytes(StandardCharsets.UTF_8));
        }
    }
}
