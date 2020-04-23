package ru.stqa.litecart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.litecart.app.ApplicationManager;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class CartPage extends Page{

    public CartPage(ApplicationManager app) {
        super(app);
    }

    public Integer getItemsQuantity() {
        return driver.findElements(By.className("image-wrapper")).size();
    }

    public String getProductName() {
        return wait.until(visibilityOfElementLocated(By
                .cssSelector(".item:first-child strong"))).getText();
    }

    public WebElement findProductElementInTableByName(String productName) {
        return driver.findElement(By.xpath("//td[text()='" + productName + "']"));
    }

    public CartPage clickRemovingItemButton() {
        wait.until(elementToBeClickable(driver.findElement(By
                .cssSelector(".item:first-child [name='remove_cart_item']")))).click();
        return this;
    }
}
