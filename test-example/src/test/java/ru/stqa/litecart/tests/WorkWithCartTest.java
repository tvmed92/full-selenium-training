package ru.stqa.litecart.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import ru.stqa.litecart.BaseTest;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class WorkWithCartTest extends BaseTest {

    @Test(description = "Задание 13. Сценарий работы с корзиной")
    public void testWorkingWithCart() {
        for (int i = 0; i < 3; i++) {
            openMainPage();
            driver.findElement(By.className("product")).click();
            if (isElementPresent("options[Size]")) {
                selectFromDropdown("[name='options[Size]']", "Small");
            }
            driver.findElement(By.name("add_cart_product")).click();
            int quantity = i + 1;
            wait.until(d -> d.findElement(By.cssSelector("#cart-wrapper .quantity")).getText()
                    .equals(String.valueOf(quantity)));
        }
        driver.findElement(By.cssSelector("#cart-wrapper .link")).click();
        int itemQuantity = driver.findElements(By.className("image-wrapper")).size();
        for (int j = 0; j < itemQuantity; j++) {
            String productName = wait.until(visibilityOfElementLocated(By
                    .cssSelector(".item:first-child strong"))).getText();
            WebElement element = driver.findElement(By.xpath("//td[text()='" + productName + "']"));
            wait.until(elementToBeClickable(driver.findElement(By
                    .cssSelector(".item:first-child [name='remove_cart_item']")))).click();
            wait.until(stalenessOf(element));
        }
    }

    private boolean isElementPresent(String cssName) {
        return driver.findElements(By.name(cssName)).size() > 0;
    }
}
