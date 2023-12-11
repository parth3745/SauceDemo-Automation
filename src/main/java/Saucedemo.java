import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class Saucedemo {

    WebDriver driver;
    ChromeOptions op;

    @BeforeClass
    public void init() {
        WebDriverManager.chromedriver().setup();
        op = new ChromeOptions();
        op.addArguments("--disable-notifications");
        driver = new ChromeDriver(op);
        driver.get("https://www.saucedemo.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        PageFactory.initElements(driver, this);
    }

    @DataProvider(name = "correctUsernames")
    public Object[][] correctUsernamesData() {
        return new Object [][] {{"standard_user", "secret_sauce"},
                {"locked_out_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"},
                {"error_user", "secret_sauce"},
                {"visual_user", "secret_sauce"}};
    }

    int count = 1;
    @Test(priority = 1, dataProvider = "correctUsernames")
    public void correctLoginTest(String username, String password) throws IOException {
        System.out.println("Skipping.");
        String expectedMessage = "Epic sadface: Username and password do not match any user in this service";
        usernameInput.clear();
        usernameInput.sendKeys(username);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        loginBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        Utilities u = new Utilities(driver);
        u.saveScreenshot("loginScreen" + String.valueOf(count));
        count++;
        boolean incorrectMsgInvisible = true;
        try {
            incorrectMsgInvisible = wait.until(ExpectedConditions.invisibilityOf(incorrectLoginMessage));
            incorrectMsgInvisible = true;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            incorrectMsgInvisible = false;
            if (username == "locked_out_user") {
                Assert.assertEquals(incorrectLoginMessage.getText(), "Epic sadface: Sorry, this user has been locked out.");
            }
            else {
                Assert.assertEquals(incorrectLoginMessage.getText(), "Epic sadface: Username and password do not match any user in this service");
            }
        }
        System.out.println("Incorrect credentials message is there?: " + !incorrectMsgInvisible);
        System.out.println(username + ", " + password + "\n");
        driver.navigate().to("https://www.saucedemo.com/");
    }

    int cartCount = 0;

    @Test(priority = 2)
    @Parameters({"itemName1", "itemName2"})
    public void addToCartTest(String item1, String item2) {
        usernameInput.clear();
        usernameInput.sendKeys("standard_user");
        passwordInput.clear();
        passwordInput.sendKeys("secret_sauce");
        loginBtn.click();
//        List<WebElement> items = driver.findElements(By.xpath("//div[@class='inventory_item_description']"));
        for (WebElement e: items) {
            if (e.findElement(By.className("inventory_item_name")).getText().equalsIgnoreCase(item1) || e.findElement(By.className("inventory_item_name")).getText().equalsIgnoreCase(item2)) {
                e.findElement(By.tagName("button")).click();
                cartCount++;
                Assert.assertEquals(e.findElement(By.tagName("button")).getText(), "Remove");
                System.out.println("Button text changed");
            }
        }
        Assert.assertEquals(cartCountBanner.getText(), String.valueOf(cartCount));
        System.out.println("Cart count is correct");
    }

    @Test(priority = 3)
    @Parameters({"itemName1", "itemName2"})
    public void cartItemsTest(String item1, String item2) {
        viewCartBtn.click();

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
