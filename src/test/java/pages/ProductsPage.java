package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ProductsPage {

    private WebDriver driver;

    private By pageTitle = By.className("title");
    private By addBackpackButton = By.id("add-to-cart-sauce-labs-backpack");
    private By addBikeLightButton = By.id("add-to-cart-sauce-labs-bike-light");
    private By cartBadge = By.className("shopping_cart_badge");
    private By cartLink = By.className("shopping_cart_link");
    private By sortDropdown = By.className("product_sort_container");
    private By prices = By.className("inventory_item_price");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle() {
        return driver.findElement(pageTitle).getText();
    }

    public void addBackpack() {
        driver.findElement(addBackpackButton).click();
    }

    public void addBikeLight() {
        driver.findElement(addBikeLightButton).click();
    }

    public String getCartCount() {
        return driver.findElement(cartBadge).getText();
    }

    public void openCart() {
        driver.findElement(cartLink).click();
    }

    public void sortByPriceLowToHigh() {
        Select select = new Select(driver.findElement(sortDropdown));
        select.selectByValue("lohi");
    }

    public List<Double> getPrices() {
        List<Double> priceList = new ArrayList<>();
        for (WebElement element : driver.findElements(prices)) {
            priceList.add(Double.parseDouble(element.getText().replace("$", "")));
        }
        return priceList;
    }
}