package org.example.core;

import org.example.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    private static final Duration WAIT_TIMEOUT = Duration.ofSeconds(10);

    protected WebElement find(By locator) {
        return wait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void click(By locator) {
        wait().until(ExpectedConditions.elementToBeClickable(locator)).click();
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
        return DriverManager.getDriver().getCurrentUrl();
    }

    private WebDriverWait wait() {
        return new WebDriverWait(DriverManager.getDriver(), WAIT_TIMEOUT);
    }
}
