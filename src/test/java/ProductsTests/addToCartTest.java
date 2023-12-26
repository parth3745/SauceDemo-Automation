package ProductsTests;

import PageObjects.LoginPage;
import PageObjects.ProductsPage;
import UtilityClasses.Initializer;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class addToCartTest {

    WebDriver driver;
    Initializer init;

    @BeforeClass
    public void initialize() throws IOException {
        init = new Initializer(driver);
        init.launchApp();
        driver = init.getDriver();
    }

    @Test
    public void cartTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.perFormLogIn("standard_user", "secret_sauce");
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addProductToCart("Sauce Labs Onesie");
    }
}
