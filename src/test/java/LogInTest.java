import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LogInTest {


    public void performLogin(String username, String password) {
        usernameInput.clear();
        usernameInput.sendKeys(username);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        loginBtn.click();
    }

    @FindBy(id = "user-name")
    WebElement usernameInput;

    @FindBy(id = "password")
    WebElement passwordInput;

    @FindBy(id = "login-button")
    WebElement loginBtn;

    @FindBy(xpath = "//h3[@data-test='error']")
    WebElement incorrectLoginMessage;

    @FindBy(css = "a.shopping_cart_link")
    WebElement viewCartBtn;

    @FindAll({@FindBy(xpath = "//div[@class='inventory_item_description']")})
    List<WebElement> items;

    @FindBy(css = "span.shopping_cart_badge")
    WebElement cartCountBanner;
}
