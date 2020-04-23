package ru.stqa.litecart;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import ru.stqa.litecart.app.ApplicationManager;

public class BaseTest {

    protected static ApplicationManager app;

    static {
        app = new ApplicationManager();
    }

    @BeforeClass
    public void setApp() {
        app.init();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

    public void openMainPage() {
        app.getDriver().get("http://localhost/litecart/en/");
    }

//    protected void loginAsUser(String email, String password) {
//        fillField("email", email);
//        fillField("password", password);
//        driver.findElement(By.name("login")).click();
//    }

//    protected void userLogout() {
//        driver.findElement(By.cssSelector("#box-account a[href='http://localhost/litecart/en/logout']")).click();
//    }

//    protected void clickCreateAccountButton() {
//        driver.findElement(By.name("create_account")).click();
//    }

//    protected void selectFromDropdown(String css, String value) {
//        Select select = new Select(driver.findElement(By.cssSelector(css)));
//        select.selectByVisibleText(value);
//    }

//    protected void fillField(String fieldName, String value) {
//        driver.findElement(By.name(fieldName)).sendKeys(value);
//    }

//    protected void fillFieldFromHome(String fieldName, String value) {
//        driver.findElement(By.name(fieldName)).sendKeys(Keys.HOME + value);
//    }

//    protected String getText(String s) {
//        return driver.findElement(By.cssSelector(s)).getText();
//    }

//    @NotNull
//    protected Double getFontSize(String css) {
//        return Double.valueOf(driver.findElement(By.cssSelector(css))
//                .getCssValue("font-size").replace("px", ""));
//    }

//    protected String getAttributeValue(String css, String value) {
//        return driver.findElement(By.cssSelector(css))
//                .getCssValue(value);
//    }
}