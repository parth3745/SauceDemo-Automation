package UtilityClasses;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
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

        if (System.getProperty("browser") == null) {
            if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
//            ChromeOptions options = new ChromeOptions();
//            options.addArguments("--headless=new");
                driver = new ChromeDriver();
                this.driver = driver;
            }
            else if(prop.getProperty("browser").equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                this.driver = driver;
            }
            else if(prop.getProperty("browser").equalsIgnoreCase("edge")) {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                this.driver = driver;
            }
        }
        else {
            if (System.getProperty("browser").equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
//            ChromeOptions options = new ChromeOptions();
//            options.addArguments("--headless=new");
                driver = new ChromeDriver();
                this.driver = driver;
            }
            else if(System.getProperty("browser").equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                this.driver = driver;
            }
            else if(System.getProperty("browser").equalsIgnoreCase("edge")) {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                this.driver = driver;
            }
        }
    }

    public void launchApp() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    public void goToHomePage() {
        driver.navigate().to(prop.getProperty("appURL"));
    }

    public WebDriver getDriver() {
        System.out.println("Driver returned.");
        return driver;
    }
}
