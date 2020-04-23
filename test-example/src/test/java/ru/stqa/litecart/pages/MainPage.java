package ru.stqa.litecart.pages;

import org.openqa.selenium.By;
import ru.stqa.litecart.app.ApplicationManager;

public class MainPage extends Page {

    public MainPage(ApplicationManager app) {
        super(app);
    }

    public MainPage chooseFirstProduct() {
        click(By.className("product"));
        return this;
    }

    protected void loginAsUser(String email, String password) {
        fillField("email", email);
        fillField("password", password);
        driver.findElement(By.name("login")).click();
    }

    protected void clickCreateAccountButton() {
        driver.findElement(By.name("create_account")).click();
    }
}
