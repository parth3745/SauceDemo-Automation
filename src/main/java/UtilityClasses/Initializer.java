package UtilityClasses;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Initializer {
    WebDriver driver;
    Properties prop;

    public Initializer(WebDriver driver) throws IOException {
        prop = new Properties();
        prop.load(new FileReader(System.getProperty("user.dir") + "/Configuration.properties"));

        if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            this.driver = driver;
        }
        else if(prop.getProperty("browser").equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            this.driver = driver;
        }
    }

    public void launchApp() {
        driver.navigate().to(prop.getProperty("appURL"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    public WebDriver getDriver() {
        return driver;
    }
}
