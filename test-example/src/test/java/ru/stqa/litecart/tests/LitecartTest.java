package ru.stqa.litecart.tests;

import com.google.common.collect.Ordering;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import ru.stqa.litecart.BaseTest;

import java.util.List;

import static com.google.common.collect.Ordering.natural;
import static java.util.stream.Collectors.toList;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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

    @Test(description = "Задание 9.1.Проверить сортировку стран в админке")
    public void testCountriesOrder() {
        loginAsAdmin();
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");

        List<WebElement> rows = driver.findElements(By.className("row"));
        List<String> countriesList = rows.stream()
                .map(row -> row.findElement(By.cssSelector("td a")).getText())
                .collect(toList());
        assertTrue(natural().isOrdered(countriesList));

        List<String> countryWithZonesLinks = rows.stream()
                .filter(row -> !row.findElement(By.cssSelector("td:nth-child(6)")).getText().equals("0"))
                .map(country -> country.findElement(By.cssSelector("td a")).getAttribute("href"))
                .collect(toList());

        for (String countryWithZonesLink : countryWithZonesLinks) {
            driver.get(countryWithZonesLink);
            List<WebElement> areas = driver.findElements(By.cssSelector("[name$='][name]']"));
            List<String> areasList = areas.stream()
                    .map(area -> area.getAttribute("value"))
                    .collect(toList());
            assertTrue(natural().isOrdered(areasList));
        }
    }

    @Test(description = "Задание 9.2.Проверить сортировку геозон в админке")
    public void testGeoZonesOrder() {
        loginAsAdmin();
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");

        List<WebElement> rows = driver.findElements(By.className("row"));
        List<String> countryWithZonesLinks = rows.stream()
                .map(row -> row.findElement(By.cssSelector("td a")).getAttribute("href"))
                .collect(toList());

        for (String countryWithZonesLink : countryWithZonesLinks) {
            driver.get(countryWithZonesLink);
            List<WebElement> areas = driver.findElements(By.cssSelector("[name$='[zone_code]'] option[selected]"));
            List<String> areasList = areas.stream().map(WebElement::getText).collect(toList());
            System.out.println(areasList);
            assertTrue(Ordering.natural().isOrdered(areasList));
        }
    }
}
