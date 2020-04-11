package ru.stqa.litecart;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static java.time.Duration.ofSeconds;
import static java.util.concurrent.TimeUnit.SECONDS;

public class BaseTest {

    protected WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, SECONDS);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, ofSeconds(10));
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
        driver = null;
    }

    public void loginAsAdmin() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("loginAsUser")).click();
        wait.until(WebDriver::getTitle).equals("My Store");
    }

    public void openMainPage() {
        driver.get("http://localhost/litecart/en/");
    }


    protected void clickCreateAccountButton() {
        driver.findElement(By.name("create_account")).click();
    }

    protected void loginAsUser(String email, String password) {
        fillField("email", email);
        fillField("password", password);
        driver.findElement(By.name("login")).click();
    }

    protected void userLogout() {
        driver.findElement(By.cssSelector("#box-account a[href='http://localhost/litecart/en/logout']")).click();
    }

    protected void selectFromDropdown(String css, String value) {
        Select select = new Select(driver.findElement(By.cssSelector(css)));
        select.selectByVisibleText(value);
    }

    protected void fillField(String fieldName, String value) {
        driver.findElement(By.name(fieldName)).sendKeys(value);
    }

    protected String getText(String s) {
        return driver.findElement(By.cssSelector(s)).getText();
    }

    @NotNull
    protected Double getFontSize(String css) {
        return Double.valueOf(driver.findElement(By.cssSelector(css))
                .getCssValue("font-size").replace("px", ""));
    }

    protected String getAttributeValue(String css, String color) {
        return driver.findElement(By.cssSelector(css))
                .getCssValue(color);
    }
}