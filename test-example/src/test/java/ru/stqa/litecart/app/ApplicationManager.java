package ru.stqa.litecart.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.litecart.pages.CartPage;
import ru.stqa.litecart.pages.MainPage;
import ru.stqa.litecart.pages.Page;
import ru.stqa.litecart.pages.ProductPage;

import java.util.concurrent.TimeUnit;

import static java.time.Duration.ofSeconds;

public class ApplicationManager {


    private WebDriver driver;
    private Page page;
    private MainPage main;
    private ProductPage product;
    private CartPage cart;
    private WebDriverWait wait;

    public ApplicationManager() {}

    public void init() {
        page = new Page(this);
    }

    public void stop() {
        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        if (driver == null) {
            driver = new ChromeDriver();
            driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
        return driver;
    }

    public WebDriverWait getWaiter() {
        wait = new WebDriverWait(driver, ofSeconds(10));
        return wait;
    }

    public MainPage main() {
        if (main == null) {
            main = new MainPage(this);
        }
        return main;
    }

    public ProductPage product() {
        if (product == null) {
            product = new ProductPage(this);
        }
        return product;
    }

    public CartPage cart() {
        if (cart == null) {
            cart = new CartPage(this);
        }
        return cart;
    }
}
