package ru.stqa.litecart.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import ru.stqa.litecart.BaseTest;

import java.util.List;

public class LitecartTest extends BaseTest {

    @Test
    public void testAdminBoxAppsMenu() {
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
}
