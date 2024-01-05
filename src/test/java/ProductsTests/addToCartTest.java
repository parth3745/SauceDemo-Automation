package ProductsTests;

import PageObjects.LoginPage;
import PageObjects.ProductsPage;
import UtilityClasses.Initializer;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class addToCartTest {

    WebDriver driver;
    Initializer init;
    LoginPage loginPage;
    ProductsPage productsPage;

    @DataProvider(name = "productsToOrder")
    public Object[] productsToOrder() throws IOException {
        JsonParser parser = new JsonParser();
        JsonElement e = parser.parse(FileUtils.readFileToString(new File(System.getProperty("user.dir") + "/Test-data/productsToOrder.json")));
        JsonArray a  = e.getAsJsonArray();
        Object[] prods = new Object[a.size()];
        for (int i = 0; i < a.size(); i++) {
            prods[i] = a.get(i).toString().substring(1, a.get(i).toString().length() - 1);
        }

        return prods;
    }

    @BeforeClass
    public void initialize() throws IOException {
        init = new Initializer(driver);
        init.launchApp();
        driver = init.getDriver();
        loginPage = new LoginPage(driver);
        loginPage.perFormLogIn("standard_user", "secret_sauce");
        productsPage = new ProductsPage(driver);
    }

    @Test(dataProvider = "productsToOrder")
    public void cartTest(String p) {
        productsPage.addProductToCart(p);
    }

    @AfterClass
    public void closure() {
        driver.close();
    }
}
