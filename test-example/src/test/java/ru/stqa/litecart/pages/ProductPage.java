package ru.stqa.litecart.pages;

import org.openqa.selenium.By;
import ru.stqa.litecart.app.ApplicationManager;

public class ProductPage extends Page{

    public ProductPage(ApplicationManager app) {
        super(app);
    }

    public ProductPage selectProductSizeIfPossible(String size) {
        if (app.product().isElementPresent(By.name("options[Size]"))) {
            selectFromDropdown(By.cssSelector("[name='options[Size]"), size);
        }
        return this;
    }

    public ProductPage clickAddToCartButton() {
        click(By.name("add_cart_product"));
        return this;
    }

    public ProductPage waitQuantityIncreased(int quantity) {
        wait.until(d -> d.findElement(By.cssSelector("#cart-wrapper .quantity")).getText()
                .equals(String.valueOf(quantity)));
        return this;
    }

    public void goToCart() {
        click(By.cssSelector("#cart-wrapper .link"));
    }
}
