package org.example.core;

import io.qameta.allure.Attachment;
import org.example.config.TestConfig;
import org.example.driver.DriverFactory;
import org.example.driver.DriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public abstract class BaseTest {

    protected TestConfig config = new TestConfig();

    @BeforeEach
    public void setUp() {
        WebDriver driver = DriverFactory.createDriver(config);
        DriverManager.setDriver(driver);
        driver.manage().timeouts().implicitlyWait(config.getImplicitTimeout());
        driver.manage().timeouts().pageLoadTimeout(config.getPageLoadTimeout());
        driver.manage().window().maximize();
        driver.get(config.getBaseUrl());
    }

    @AfterEach
    public void tearDown() {
        WebDriver driver = getSafeDriver();
        if (driver != null) {
            takeScreenshot();
            DriverManager.quitDriver();
        }
    }

    private WebDriver getSafeDriver() {
        try {
            return DriverManager.getDriver();
        } catch (IllegalStateException ignored) {
            return null;
        }
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] takeScreenshot() {
        WebDriver driver = getSafeDriver();
        if (driver instanceof TakesScreenshot takesScreenshot) {
            return takesScreenshot.getScreenshotAs(OutputType.BYTES);
        }
        return new byte[0];
    }
}
