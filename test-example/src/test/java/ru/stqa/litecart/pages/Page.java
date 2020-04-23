package ru.stqa.litecart.pages;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.litecart.app.ApplicationManager;

import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;

public class Page {

    protected WebDriver driver;
    protected ApplicationManager app;
    protected WebDriverWait wait;

    public Page(ApplicationManager app) {
        this.app = app;
        this.driver = app.getDriver();
        this.wait = app.getWaiter();
    }

    public Page() {
    }

    protected void click(By locator) {
        driver.findElement(locator).click();
    }

    protected void selectFromDropdown(By locator, String value) {
        Select select = new Select(driver.findElement(locator));
        select.selectByVisibleText(value);
    }

    protected boolean isElementPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    public void waitStalenessOfElementInTable(WebElement element) {
        wait.until(stalenessOf(element));
    }

    protected void fillField(String fieldName, String value) {
        driver.findElement(By.name(fieldName)).sendKeys(value);
    }

    protected void fillFieldFromHome(String fieldName, String value) {
        driver.findElement(By.name(fieldName)).sendKeys(Keys.HOME + value);
    }

    protected String getText(By locator) {
        return driver.findElement(locator).getText();
    }

    @NotNull
    protected Double getFontSize(String css) {
        return Double.valueOf(driver.findElement(By.cssSelector(css))
                .getCssValue("font-size").replace("px", ""));
    }

    protected String getAttributeValue(String css, String value) {
        return driver.findElement(By.cssSelector(css))
                .getCssValue(value);
    }

    public void loginAsAdmin() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(WebDriver::getTitle).equals("My Store");
    }

    protected void userLogout() {
        driver.findElement(By.cssSelector("#box-account a[href='http://localhost/litecart/en/logout']")).click();
    }

    protected void goToCountries() {
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
    }
}
