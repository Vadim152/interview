package org.example.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.core.BaseTest;
import org.example.pages.CartPage;
import org.example.pages.LoginPage;
import org.example.pages.ProductsPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("E2E")
@Feature("Покупка товара")
public class PurchaseTest extends BaseTest {

    @Test
    @Story("Покупка одного товара стандартным пользователем")
    @DisplayName("Пользователь может залогиниться и добавить товар в корзину")
    void userCanLoginAndAddProductToCart() {
        // 1. Логин
        LoginPage loginPage = new LoginPage();
        ProductsPage productsPage = loginPage.loginAs("standard_user", "secret_sauce");

        // 2. Проверяем, что попали на страницу товаров
        assertThat(productsPage.isOpened())
                .as("Страница товаров должна быть открыта")
                .isTrue();

        // 3. Добавляем товар в корзину
        String productName = "Sauce Labs Backpack";
        productsPage.addProductToCart(productName);

        // 4. Открываем корзину
        CartPage cartPage = productsPage.openCart();

        // 5. Проверяем, что товар есть в корзине
        assertThat(cartPage.hasProduct(productName))
                .as("Товар должен отображаться в корзине")
                .isTrue();
    }
}
