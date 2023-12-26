package LoginTest;

import PageObjects.LoginPage;
import PageObjects.ProductsPage;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import lombok.extern.java.Log;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import UtilityClasses.Initializer;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class LoginTest {

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
    public void performLogin(String username, String password) {
        init.goToHomePage();
        LoginPage loginPage = new LoginPage(driver);
        String message = loginPage.perFormLogIn(username.substring(1, username.length() - 1), password.substring(1, password.length() - 1));
        if (username.contains("locked_out_user")) {
            Assert.assertEquals(message, "Epic sadface: Sorry, this user has been locked out.");
        }
        else if(username.contains("nonExistingUser")) {
            Assert.assertEquals(message, "Epic sadface: Username and password do not match any user in this service");
        }
        init.goToHomePage();
    }

    @AfterClass
    public void closure() {
        driver.close();
    }
}
