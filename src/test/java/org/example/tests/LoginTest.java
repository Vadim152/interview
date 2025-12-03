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

@Epic("Authentication")
@Feature("Login")
class LoginTest extends BaseTest {

    @Test
    @Story("Successful login with valid credentials")
    @DisplayName("User can login with standard_user")
    void userCanLogin() {
        LoginPage loginPage = new LoginPage();
        ProductsPage productsPage = loginPage.loginAs("standard_user", "secret_sauce");
        assertTrue(productsPage.isOpened());
    }

    @Test
    @Story("Error message for invalid password")
    void userSeesErrorOnInvalidPassword() {
        LoginPage loginPage = new LoginPage();
        loginPage.loginAs("standard_user", "wrong");
        assertEquals("Epic sadface: Username and password do not match any user in this service", loginPage.getErrorText());
    }
}
