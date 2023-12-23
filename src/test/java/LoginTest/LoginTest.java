package LoginTest;

import PageObjects.LoginPage;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import UtilityClasses.Initializer;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;

public class LoginTest {

    WebDriver driver;

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

    @BeforeTest
    public void initialize() throws IOException {
        Initializer init = new Initializer(driver);
        init.launchApp();
        driver = init.getDriver();
    }

    @Test(dataProvider = "credentials")
    public void performLogin(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        String message = loginPage.perFormLogIn(username.substring(1, username.length() - 1), password.substring(1, password.length() - 1));
        if (username.contains("locked_out_user")) {
            Assert.assertEquals(message, "Epic sadface: Sorry, this user has been locked out.");
        }
        else if(username.contains("nonExistingUser")) {
            Assert.assertEquals(message, "Epic sadface: Username and password do not match any user in this service");
        }
    }
}
