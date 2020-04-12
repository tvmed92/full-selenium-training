package ru.stqa.litecart.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.stqa.litecart.BaseTest;

public class LitecartLogUp extends BaseTest {

    private String email = RandomStringUtils.randomAlphanumeric(12) + "@email.rand";
    private String password = RandomStringUtils.randomAlphanumeric(12);
    private String alphabetic = RandomStringUtils.randomAlphabetic(10);
    private String numeric = RandomStringUtils.randomNumeric(5);

    @Test(description = "Задание 11. Сценарий регистрации пользователя")
    public void testLitecartLogUp() {
        openMainPage();
        driver.findElement(By.name("login_form")).findElement(By.cssSelector("a")).click();
        fillField("firstname", alphabetic);
        fillField("lastname", alphabetic);
        fillField("address1", alphabetic);
        fillField("postcode", numeric);
        fillField("city", alphabetic);
        selectFromDropdown(".content select[name='country_code']", "United States");
        fillField("email", email);
        fillFieldFromHome("phone", "+134567890");
        fillField("password", password);
        fillField("confirmed_password", password);
        clickCreateAccountButton();
        selectFromDropdown("select[name='zone_code']", "Oklahoma");
        fillField("password", password);
        fillField("confirmed_password", password);
        clickCreateAccountButton();

        userLogout();
        loginAsUser(email, password);
        userLogout();
    }
}
