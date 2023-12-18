package LoginTest;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import Utilities.Initializer;

import java.io.IOException;

public class LoginTest {

    WebDriver driver;

    @BeforeTest
    public void initialize() throws IOException {
        Initializer init = new Initializer(driver);
        init.launchApp();
        driver = init.getDriver();
    }
}
