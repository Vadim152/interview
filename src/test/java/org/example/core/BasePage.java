package org.example.core;

import org.example.config.TestConfig;
import org.example.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public abstract class BasePage {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasePage.class);

    protected final WebDriver driver;
    private final WebDriverWait wait;

    protected BasePage() {
        this.driver = DriverManager.getDriver();
        Duration implicitTimeout = new TestConfig().getImplicitTimeout();
        this.wait = new WebDriverWait(driver, implicitTimeout);
        LOGGER.debug("Initialized BasePage with implicit timeout: {}", implicitTimeout);
    }

    protected WebDriver getDriver() {
        return driver;
    }

    protected WebElement waitForVisible(By locator) {
        LOGGER.debug("Waiting for visibility of element located by: {}", locator);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void click(By locator) {
        LOGGER.debug("Clicking element located by: {}", locator);
        waitForVisible(locator).click();
    }

    protected void type(By locator, String text) {
        LOGGER.debug("Typing into element located by: {}. Text: {}", locator, text);
        WebElement element = waitForVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(By locator) {
        LOGGER.debug("Getting text from element located by: {}", locator);
        return waitForVisible(locator).getText();
    }
}
