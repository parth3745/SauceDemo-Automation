package Pages;

import UtilityClasses.UtilityMethods;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductsPage extends UtilityMethods {
    WebDriver driver;

    public ProductsPage(WebDriver driver) {
        super(driver);
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

    public void addProductToCart(String prodTitle) {
        for (WebElement e: prodCards) {
            if (e.findElement(By.className("inventory_item_name")).getText().equalsIgnoreCase(prodTitle)) {
                e.findElement(By.tagName("button")).click();
            }
        }
    }

    public void removeProductFromCart(String prodTitle) {
        for (WebElement e: prodCards) {
            if (e.findElement(By.className("inventory_item_name")).getText().equalsIgnoreCase(prodTitle)) {
                e.findElement(By.tagName("button")).click();
            }
        }
    }

    public int getBadgeCount() throws InterruptedException {
        Thread.sleep(500);
        try {
                return Integer.parseInt(cartBadge.getText());
        }
        catch (Exception e) {
            return 0;
        }
    }

    public void goToCartPage() {
        cartButton.click();
    }
    
    @FindAll({@FindBy(className = "inventory_item_name")})
    private List<WebElement> prodTitles;

    @FindAll({@FindBy(className = "inventory_item_desc")})
    private List<WebElement> prodDescriptions;

    @FindAll({@FindBy(className = "inventory_item_price")})
    private List<WebElement> prodPrices;

    @FindAll({@FindBy(className = "inventory_item_description")})
    private List<WebElement> prodCards;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartButton;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;
}
