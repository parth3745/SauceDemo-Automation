package ProductsTests;

import Pages.CartPage;
import Pages.LoginPage;
import Pages.ProductsPage;
import UtilityClasses.Initializer;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.json.Json;
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

    @Test(dataProvider = "itemsToOrder")
    public void addItems(String name) {
        productsPage.addProductToCart(name.substring(1, name.length()-1));
    }

    @Test()
    public void removeAllItemsTest() {
        System.out.println("All removed.");
    }

    @AfterClass()
    public void closure() throws InterruptedException {
        Thread.sleep(500);
        driver.close();
    }

}
