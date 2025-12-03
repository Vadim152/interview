package org.example.pages;

import io.qameta.allure.Step;
import org.example.core.BasePage;
import org.example.dto.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsPage extends BasePage {

    private static final By TITLE = By.cssSelector("span.title");
    private static final By CART_ICON = By.id("shopping_cart_container");
    private static final By PRODUCT_ITEMS = By.cssSelector(".inventory_item");
    private static final By PRODUCT_NAME = By.className("inventory_item_name");
    private static final By PRODUCT_PRICE = By.className("inventory_item_price");
    private static final ProductsPage INSTANCE = new ProductsPage();

    private ProductsPage() {
    }

    public static ProductsPage getInstance() {
        return INSTANCE;
    }

    public boolean isOpened() {
        return getCurrentUrl().contains("inventory") && find(TITLE).isDisplayed();
    }

    public int getProductsCount() {
        return driver.findElements(PRODUCT_ITEMS).size();
    }

    public List<Product> getProducts() {
        List<WebElement> items = driver.findElements(PRODUCT_ITEMS);

        return items.stream()
                .map(item -> {
                    String name = item.findElement(PRODUCT_NAME).getText();
                    String priceText = item.findElement(PRODUCT_PRICE).getText().replace("$", "");
                    double price = Double.parseDouble(priceText);
                    return new Product(name, price);
                })
                .toList();
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
        return CartPage.getInstance();
    }

}
