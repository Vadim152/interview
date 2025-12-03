package org.example.pages;

import org.example.core.BasePage;
import org.openqa.selenium.By;

public class CartPage extends BasePage {

    private static final CartPage INSTANCE = new CartPage();

    private CartPage() {
    }

    public static CartPage getInstance() {
        return INSTANCE;
    }

    public boolean hasProduct(String productName) {
        By productLocator = By.xpath(
                "//div[@class='cart_item' and .//div[text()='" + productName + "']]"
        );
        return isVisible(productLocator);
    }
}
