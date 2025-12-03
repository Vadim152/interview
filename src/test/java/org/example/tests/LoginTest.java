package org.example.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.example.core.BaseTest;
import org.example.pages.LoginPage;
import org.example.pages.ProductsPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Authentication")
@Feature("Login")
class LoginTest extends BaseTest {

    @Test
    @Story("Valid user can login")
    @DisplayName("User can log in with standard credentials")
    @Severity(SeverityLevel.CRITICAL)
    void userCanLogin() {
        LoginPage loginPage = new LoginPage(getConfig());
        loginPage.loginAs("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(getConfig());
        assertEquals("Products", productsPage.getTitle(), "Products page should be displayed after login");
    }
}
