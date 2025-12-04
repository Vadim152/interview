package org.example.core;

import org.example.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    private static final Duration WAIT_TIMEOUT = Duration.ofSeconds(10);

    protected WebElement find(By locator) {
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void click(By locator) {
        getWait().until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void type(By locator, String value) {
        WebElement element = find(locator);
        element.clear();
        element.sendKeys(value);
    }

    protected boolean isVisible(By locator) {
        try {
            return find(locator).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }

    private WebDriverWait getWait() {
        return new WebDriverWait(getDriver(), WAIT_TIMEOUT);
    }

    protected WebDriver getDriver() {
        return DriverManager.getDriver();
    }
}
