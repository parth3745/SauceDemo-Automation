package UtilityClasses;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class UtilityMethods {
    WebDriver driver;

    public UtilityMethods(WebDriver driver) {
        this.driver = driver;
    }

    public String saveScreenshot(String name) throws IOException {
        File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(f, new File(System.getProperty("user.dir") + "/Screenshots/" + name + ".png"));
        return (System.getProperty("user.dir") + "/Screenshots/" + name + ".png");
    }

    //wait for visibility of a web element
    public WebElement waitForVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
}
