package ru.stqa.litecart.tests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import ru.stqa.litecart.BaseTest;

public class WorkWithCartTest extends BaseTest {

    @Test(description = "Задание 13. Сценарий работы с корзиной")
    public void testWorkingWithCart() {
        for (int i = 0; i < 3; i++) {
            openMainPage();
            app.main().chooseFirstProduct();
            app.product().selectProductSizeIfPossible("Small");
            app.product().clickAddToCartButton();
            int quantity = i + 1;
            app.product().waitQuantityIncreased(quantity);
        }
        app.product().goToCart();
        int itemsQuantity = app.cart().getItemsQuantity();
        for (int j = 0; j < itemsQuantity; j++) {
            String productName = app.cart().getProductName();
            WebElement element = app.cart().findProductElementInTableByName(productName);
            app.cart().clickRemovingItemButton();
            app.cart().waitStalenessOfElementInTable(element);
        }
    }
}
