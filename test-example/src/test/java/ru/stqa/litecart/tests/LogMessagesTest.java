package ru.stqa.litecart.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;
import org.testng.annotations.Test;
import ru.stqa.litecart.BaseTest;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class LogMessagesTest extends BaseTest {

    @Test(description = "17. Проверить отсутствие сообщений в логе браузера")
    public void testNoLogMessagesInCatalog() {
        loginAsAdmin();
        goToCatalog();

        int productsSize = driver.findElements(By.cssSelector("[href*='product_id']:not([title])")).size();
        for (int i = 0; i < productsSize; i++) {
            List<WebElement> products = driver.findElements(By.cssSelector("[href*='product_id']:not([title])"));
            wait.until(elementToBeClickable(products.get(i))).click();
            List<LogEntry> logs = driver.manage().logs().get("browser").getAll();
            if (logs.size() != 0) {
                System.out.println("There are some js-errors on the page: " +
                        driver.getCurrentUrl() + ".\n See logs below: \n" + logs);
            }
            goToCatalog();
        }
    }

    private void goToCatalog() {
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
    }
}
