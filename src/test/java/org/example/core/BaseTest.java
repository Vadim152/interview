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

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

public abstract class BaseTest {

    @BeforeEach
    public void setUp() {
        WebDriver driver = DriverFactory.createDriver();
        driver.manage().timeouts().pageLoadTimeout(TestConfig.getTimeout());
        driver.manage().timeouts().implicitlyWait(TestConfig.getTimeout());
        DriverManager.setDriver(driver);
        driver.get(TestConfig.getBaseUrl());
    }

    @AfterEach
    public void tearDown() {
        WebDriver driver = DriverManager.getDriver();
        attachScreenshot(driver);
        attachPageSource(driver);
        driver.quit();
        DriverManager.unload();
    }

    private void attachScreenshot(WebDriver driver) {
        if (driver instanceof TakesScreenshot takesScreenshot) {
            byte[] screenshot = takesScreenshot.getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Screenshot", "image/png", new ByteArrayInputStream(screenshot), "png");
        }
    }

    private void attachPageSource(WebDriver driver) {
        String source = driver.getPageSource();
        Allure.addAttachment("Page source", "text/html", new ByteArrayInputStream(source.getBytes(StandardCharsets.UTF_8)), "html");
    }
}
