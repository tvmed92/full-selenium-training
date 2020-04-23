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
}