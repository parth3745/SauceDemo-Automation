package ProductsTests;

import Pages.LoginPage;
import Pages.ProductsPage;
import UtilityClasses.Initializer;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ProductInfoTest {
    WebDriver driver;
    Initializer init;

    @DataProvider(name = "credentials")
    public Object[] getCredentials() throws IOException {
        Object usernames = JsonParser.parseReader(new JsonReader(new FileReader(System.getProperty("user.dir") + "/Test-data/logInCredentials.json")));
        JsonObject jo = (JsonObject) usernames;
        JsonArray usernameArray = jo.getAsJsonArray("usernames");
        Object[][] credentials = new Object[usernameArray.size()][2];
        for (int i = 0; i < usernameArray.size(); i++) {
            credentials[i] = new Object[]{usernameArray.get(i).toString(), jo.get("password").toString()};
        }

        return credentials;
    }

    @BeforeClass()
    public void initialize() throws IOException {
        init = new Initializer(driver);
        init.launchApp();
        driver = init.getDriver();
    }

    @Test(dataProvider = "credentials")
    public void productsInfoVerificationTest(String username, String password) throws IOException {
        SoftAssert softAssert = new SoftAssert();
        init.goToHomePage();
        if (username.contains("locked_out_user") || username.contains("nonExistingUser")) {
            ;
        }
        else {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.perFormLogIn(username.substring(1, username.length() - 1), password.substring(1, password.length() - 1));
            ProductsPage productsPage = new ProductsPage(driver);
            String expectedJsonString = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "/Test-data/products.json"));
            JsonElement e = (new JsonParser()).parse(expectedJsonString);
            JsonArray expectedArray = e.getAsJsonArray();
            JsonArray actualArray = productsPage.getProductsInfo();
            for (int i = 0; i < expectedArray.size(); i++) {
                softAssert.assertEquals(actualArray.get(i).getAsJsonObject().get("product_name"), expectedArray.get(i).getAsJsonObject().get("product_name"));
                softAssert.assertEquals(actualArray.get(i).getAsJsonObject().get("product_desc"), expectedArray.get(i).getAsJsonObject().get("product_desc"));
                softAssert.assertEquals(actualArray.get(i).getAsJsonObject().get("product_price"), expectedArray.get(i).getAsJsonObject().get("product_price"));

//                System.out.println("Actual: " + actualArray.get(i).getAsJsonObject().get("product_name") + " | Expected: " + expectedArray.get(i).getAsJsonObject().get("product_name"));
//                System.out.println("Actual: " + actualArray.get(i).getAsJsonObject().get("product_desc") + " | Expected: " + expectedArray.get(i).getAsJsonObject().get("product_desc"));
//                System.out.println("Actual: " + actualArray.get(i).getAsJsonObject().get("product_price") + " | Expected: " + expectedArray.get(i).getAsJsonObject().get("product_price"));
            }
        }
        softAssert.assertAll();
        init.goToHomePage();
    }

    @AfterClass
    public void closure() throws InterruptedException {
        Thread.sleep(500);
        driver.close();
    }
}
