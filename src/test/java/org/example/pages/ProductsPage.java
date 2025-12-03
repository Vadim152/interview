package org.example.pages;

import org.example.core.BasePage;
import org.openqa.selenium.By;

public class ProductsPage extends BasePage {

    private static final By TITLE = By.cssSelector("span.title");
    private static final By PRODUCT_ITEMS = By.cssSelector(".inventory_item");

    public boolean isOpened() {
        return getCurrentUrl().contains("inventory") && find(TITLE).isDisplayed();
    }

    public int getProductsCount() {
        return driver.findElements(PRODUCT_ITEMS).size();
    }
}
