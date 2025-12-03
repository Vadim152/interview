package org.example.pages;

import io.qameta.allure.Step;
import org.example.config.TestConfig;
import org.example.core.BasePage;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private static final By USERNAME_INPUT = By.id("user-name");
    private static final By PASSWORD_INPUT = By.id("password");
    private static final By LOGIN_BUTTON = By.id("login-button");
    private static final By ERROR_MESSAGE = By.cssSelector("h3[data-test='error']");

    @Step("Открыть страницу логина")
    public LoginPage open() {
        driver.get(TestConfig.getBaseUrl());
        return this;
    }

    @Step("Ввести имя пользователя {user}")
    public LoginPage setUsername(String user) {
        type(USERNAME_INPUT, user);
        return this;
    }

    @Step("Ввести пароль")
    public LoginPage setPassword(String pass) {
        type(PASSWORD_INPUT, pass);
        return this;
    }

    @Step("Отправить форму входа")
    public ProductsPage submitLogin() {
        click(LOGIN_BUTTON);
        return new ProductsPage();
    }

    @Step("Получить текст ошибки")
    public String getErrorText() {
        return find(ERROR_MESSAGE).getText();
    }

    @Step("Войти под пользователем {username}")
    public ProductsPage loginAs(String username, String password) {
        setUsername(username);
        setPassword(password);
        return submitLogin();
    }
}
