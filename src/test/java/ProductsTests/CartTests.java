package ProductsTests;

import Pages.CartPage;
import Pages.LoginPage;
import Pages.ProductsPage;
import UtilityClasses.Initializer;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class CartTests {

    WebDriver driver;
    Initializer init;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;

    double cartAmount;
    int cartItems;

    @BeforeClass()
    public void init() throws IOException {
        init = new Initializer(driver);
        driver = init.getDriver();
        loginPage = new LoginPage(driver);
        loginPage.performLogIn();
        productsPage = new ProductsPage(driver);
        cartAmount = 0.00;
    }

    @DataProvider(name = "itemsToOrder")
    public Object[][] getItems() throws IOException {
        String arr = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "/" + "Test-data/productsToOrder.json"));
        JsonElement e = JsonParser.parseString(arr);
        JsonArray array = e.getAsJsonArray();
        cartItems = array.size();
        Object[][] items = new Object[cartItems][1];
        for (int i = 0; i < cartItems; i++) {
            items[i] = new Object[]{array.get(i).getAsJsonObject().get("title").toString()};
            cartAmount += array.get(i).getAsJsonObject().get("price").getAsDouble();
        }
        System.out.println(cartAmount);
        return items;
    }

    @Test(priority = 1, dataProvider = "itemsToOrder")
    public void addItems(String title) {
        productsPage.addProductToCart(title.substring(1, title.length() - 1));
    }

    @Test(priority = 2)
    public void cartBadgeCountCheck() throws InterruptedException {
        Assert.assertEquals(productsPage.getBadgeCount(), cartItems);
    }

    @Test(priority = 3, dataProvider = "itemsToOrder")
    public void removeItems(String title) {
        productsPage.removeProductFromCart(title.substring(1, title.length() - 1));
    }

    @Test(priority = 4)
    public void emptyCartBadgeCountCheck() throws InterruptedException {
        Assert.assertEquals(productsPage.getBadgeCount(), 0);
    }

    @Test(priority = 5, dataProvider = "itemsToOrder")
    public void addItemsAgain(String title) {
        productsPage.addProductToCart(title.substring(1, title.length() - 1));
    }

    @AfterClass()
    public void closure() throws InterruptedException {
        productsPage.goToCartPage();
        Thread.sleep(500);
        driver.close();
    }
}
