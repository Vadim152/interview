package org.example.pages;

import org.example.core.BasePage;
import org.example.config.TestConfig;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private static final By USERNAME = By.id("user-name");
    private static final By PASSWORD = By.id("password");
    private static final By LOGIN_BUTTON = By.id("login-button");
    private static final By ERROR = By.cssSelector("[data-test='error']");

    public LoginPage(TestConfig config) {
        super(config.getImplicitTimeout());
    }

    public LoginPage loginAs(String username, String password) {
        type(USERNAME, username);
        type(PASSWORD, password);
        click(LOGIN_BUTTON);
        return this;
    }

    public boolean isErrorVisible() {
        return !getDriver().findElements(ERROR).isEmpty();
    }
}
