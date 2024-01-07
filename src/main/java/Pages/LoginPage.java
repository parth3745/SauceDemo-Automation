package Pages;

import UtilityClasses.UtilityMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends UtilityMethods {

    WebDriver driver;
    String loginErrorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String perFormLogIn(String username, String password) {
        driver.navigate().to("https://saucedemo.com");
        usernameInput.clear();
        passwordInput.clear();
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginSubmitBtn.click();
        String msg;
        try {
            WebElement message = waitForVisibility(errorMessage);
            msg = message.getText();
        }
        catch (Exception e) {
            msg = "Logged in.";
        }
        return msg;
    }

    public void closeWindow() {
        driver.close();
    }

    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginSubmitBtn;

    @FindBy(tagName = "h3")
    private WebElement errorMessage;

}
