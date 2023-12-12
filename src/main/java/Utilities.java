import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Utilities {
    WebDriver driver;

    public Utilities(WebDriver driver) {
        this.driver = driver;
        System.out.println("Utilities object created.");
    }

    public void saveScreenshot(String name) throws IOException {
        File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(f, new File(System.getProperty("user.dir") + "/Screenshots/" + name + ".png"));
    }

    public boolean waitForInVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        return wait.until(ExpectedConditions.invisibilityOf(element));
    }
}
