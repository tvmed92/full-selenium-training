package ru.stqa.litecart.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.LocalFileDetector;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.litecart.BaseTest;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.testng.Assert.assertEquals;

public class AddingNewProductTest extends BaseTest {

    private String numeric = RandomStringUtils.randomNumeric(5);
    private String todayDate;
    private File localFile;

    @BeforeClass
    public void setUp() {
        LocalFileDetector detector = new LocalFileDetector();
        localFile = detector.getLocalFile("src/test/resources/pic.jpg");
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        todayDate = format.format(new Date());
        loginAsAdmin();
    }

    @Test(description = "Задание 12. Сценарий добавления товара")
    public void testAddingNewProduct() {
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog");
        int beforeSize = driver.findElements(By.className("row")).size();

        // go to add product page -> General tab
        driver.findElement(By.cssSelector("[href$='edit_product']")).click();
        fillField("name[en]", "Kitty");
        fillField("code", numeric);
        driver.findElements(By.name("product_groups[]")).get(2).click();
        driver.findElement(By.cssSelector("input[name='quantity']")).sendKeys("2,00");
        driver.findElement(By.cssSelector("input[name='new_images[]']"))
                .sendKeys(localFile.getAbsolutePath());
        fillFieldFromHome("date_valid_from", todayDate);
        fillFieldFromHome("date_valid_to", todayDate);

        // go to Information tab
        driver.findElement(By.cssSelector("[href='#tab-information']")).click();
        selectFromDropdown("[name='manufacturer_id']", "ACME Corp.");
        fillField("keywords", "kitty, cat");
        fillField("short_description[en]", "short description");
        fillField("description[en]", "it's a very detailed description");
        fillField("head_title[en]", "Kitty title");

        // go to Prices tab
        driver.findElement(By.cssSelector("[href='#tab-prices']")).click();
        fillFieldFromHome("purchase_price", "100,00");
        selectFromDropdown("[name='purchase_price_currency_code']", "US Dollars");
        fillField("prices[USD]", "110");
        fillField("prices[EUR]", "95");
        driver.findElement(By.name("save")).click();

        int afterSize = driver.findElements(By.className("row")).size();
        assertEquals(afterSize, beforeSize + 1);
    }
}
