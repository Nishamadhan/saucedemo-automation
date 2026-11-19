package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage {

    private WebDriver driver;

    private By itemNames = By.className("inventory_item_name");
    private By removeBackpackButton = By.id("remove-sauce-labs-backpack");
    private By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<String> getItemNames() {
        List<String> names = new ArrayList<>();
        for (WebElement element : driver.findElements(itemNames)) {
            names.add(element.getText());
        }
        return names;
    }

    public void removeBackpack() {
        driver.findElement(removeBackpackButton).click();
    }

    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }
}
