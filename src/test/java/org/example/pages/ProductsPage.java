package org.example.pages;

import org.example.core.BasePage;
import org.openqa.selenium.By;

public class ProductsPage extends BasePage {

    private static final By TITLE = By.cssSelector(".title");

    public ProductsPage() {
        super();
    }

    public String getTitle() {
        return getText(TITLE);
    }
}
