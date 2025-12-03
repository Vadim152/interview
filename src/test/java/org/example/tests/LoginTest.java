package org.example.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.core.BaseTest;
import org.example.pages.LoginPage;
import org.example.pages.ProductsPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Аутентификация")
@Feature("Вход")
class LoginTest extends BaseTest {

    @Test
    @Story("Успешный вход с корректными учетными данными")
    @DisplayName("Пользователь может войти как standard_user")
    void userCanLogin() {
        LoginPage loginPage = new LoginPage();
        ProductsPage productsPage = loginPage.loginAs("standard_user", "secret_sauce");
        assertTrue(productsPage.isOpened());
    }

    @Test
    @Story("Сообщение об ошибке при неверном пароле")
    void userSeesErrorOnInvalidPassword() {
        LoginPage loginPage = new LoginPage();
        loginPage.loginAs("standard_user", "wrong");
        assertEquals("Epic sadface: Имя пользователя и пароль не совпадают ни с одним пользователем этого сервиса", loginPage.getErrorText());
    }
}
