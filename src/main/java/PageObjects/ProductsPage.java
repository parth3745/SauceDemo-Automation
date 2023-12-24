package PageObjects;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductsPage {
    WebDriver driver;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public JsonArray getProductsInfo() {
        JsonArray actualArray;
        if (prodTitles.size() == prodDescriptions.size() && prodDescriptions.size() == prodPrices.size()) {
            int size = prodTitles.size();
            actualArray = new JsonArray();
            for (int i = 0; i < size; i++) {
                JsonObject object = new JsonObject();
                object.addProperty("product_name", prodTitles.get(i).getText());
                object.addProperty("product_desc", prodDescriptions.get(i).getText());
                object.addProperty("product_price", prodPrices.get(i).getText());
                actualArray.add(object);
            }
        }
        else {
            System.out.println("Product title count, description count and prices count mismatch.");
            actualArray = null;
        }
        return actualArray;
    }

    @FindAll({@FindBy(className = "inventory_item_name")})
    private List<WebElement> prodTitles;

    @FindAll({@FindBy(className = "inventory_item_desc")})
    private List<WebElement> prodDescriptions;

    @FindAll({@FindBy(className = "inventory_item_price")})
    private List<WebElement> prodPrices;
}
