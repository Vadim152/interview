package org.example.pages;

import io.qameta.allure.Step;
import org.example.core.BasePage;
import org.openqa.selenium.By;

public class ProductsPage extends BasePage {

    private static final By TITLE = By.cssSelector("span.title");
    private static final By CART_ICON = By.id("shopping_cart_container");
    private static final By PRODUCT_ITEMS = By.cssSelector(".inventory_item");

    public boolean isOpened() {
        return getCurrentUrl().contains("inventory") && find(TITLE).isDisplayed();
    }

    public int getProductsCount() {
        return driver.findElements(PRODUCT_ITEMS).size();
    }

    @Step("Добавляем товар '{productName}' в корзину")
    public void addProductToCart(String productName) {
        By addToCartButton = By.xpath(
                "//div[@class='inventory_item' and .//div[text()='" + productName + "']]//button"
        );
        click(addToCartButton);
    }

    @Step("Открываем корзину")
    public CartPage openCart() {
        click(CART_ICON);
        return new CartPage();
    }
}
