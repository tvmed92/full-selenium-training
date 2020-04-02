package ru.stqa.litecart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static java.time.Duration.ofSeconds;
import static java.util.concurrent.TimeUnit.SECONDS;

public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, SECONDS);
        wait = new WebDriverWait(driver, ofSeconds(10));
        login();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
        driver = null;
    }

    private void login() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(WebDriver::getTitle).equals("My Store");
    }
}