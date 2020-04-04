package ru.stqa.litecart.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import ru.stqa.litecart.BaseTest;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class LitecartTest extends BaseTest {

    @Test(description = "Задание 7. Сделайте сценарий, проходящий по всем разделам админки")
    public void testAdminBoxAppsMenu() {
        loginAsAdmin();
        List<WebElement> apps = driver.findElements(By.cssSelector("#app-"));

        for (int i = 0; i < apps.size(); i++) {
            driver.findElements(By.cssSelector("#app-")).get(i).click();
            List<WebElement> subApps = driver.findElements(By.cssSelector("#app-")).get(i)
                    .findElements(By.cssSelector("li"));

            for (int j = 0; j < subApps.size(); j++) {
                driver.findElements(By.cssSelector("#app-")).get(i)
                        .findElements(By.cssSelector("li")).get(j).click();
                driver.findElement(By.tagName("h1")).isDisplayed();
            }
        }
    }

    @Test(description = "Задание 8. Сделайте сценарий, проверяющий наличие стикеров у товаров")
    public void testOneGoodOneSticker() {
        openMainPage();
        List<WebElement> goods = driver.findElements(By.className("product"));
        for (WebElement good : goods) {
            int stickerSize = good.findElements(By.className("sticker")).size();
            assertEquals(stickerSize, 1);
        }
    }
}
