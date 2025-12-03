package org.example.tests;

import org.example.core.BaseTest;
import org.example.dto.Product;
import org.example.pages.LoginPage;
import org.example.pages.ProductsPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductsPriceStreamTest extends BaseTest {

    @Test
    @DisplayName("Проверка наличия товаров дешевле 15$")
    void shouldFindProductsCheaperThan15Dollars() {
        LoginPage loginPage = LoginPage.getInstance();
        ProductsPage productsPage = loginPage.loginAs("standard_user", "secret_sauce");

        List<Product> products = productsPage.getProducts();

        List<Product> cheapProducts = products.stream()
                .filter(product -> product.getPrice() < 15.0)
                .toList();

        assertFalse(cheapProducts.isEmpty(), "Должны быть товары дешевле 15$");

        assertTrue(
                cheapProducts.stream().allMatch(product -> product.getPrice() < 15.0),
                "Все найденные товары должны стоить меньше 15$"
        );
    }
}
