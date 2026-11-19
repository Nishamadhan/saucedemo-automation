package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CartTests extends BaseTest {

    private ProductsPage loginAndGetProductsPage() {
        new LoginPage(driver).login("standard_user", "secret_sauce");
        return new ProductsPage(driver);
    }

    @Test
    public void addOneItemToCart() {
        ProductsPage productsPage = loginAndGetProductsPage();
        productsPage.addBackpack();

        Assert.assertEquals(productsPage.getCartCount(), "1");
    }

    @Test
    public void addMultipleItemsToCart() {
        ProductsPage productsPage = loginAndGetProductsPage();
        productsPage.addBackpack();
        productsPage.addBikeLight();
        productsPage.openCart();

        CartPage cartPage = new CartPage(driver);
        List<String> items = cartPage.getItemNames();
        Assert.assertEquals(items.size(), 2);
        Assert.assertTrue(items.contains("Sauce Labs Backpack"));
        Assert.assertTrue(items.contains("Sauce Labs Bike Light"));
    }

    @Test
    public void removeItemFromCart() {
        ProductsPage productsPage = loginAndGetProductsPage();
        productsPage.addBackpack();
        productsPage.openCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.removeBackpack();

        Assert.assertEquals(cartPage.getItemNames().size(), 0);
    }

    @Test
    public void sortPriceLowToHigh() {
        ProductsPage productsPage = loginAndGetProductsPage();
        productsPage.sortByPriceLowToHigh();

        List<Double> actualPrices = productsPage.getPrices();
        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        Collections.sort(expectedPrices);

        Assert.assertEquals(actualPrices, expectedPrices);
    }
}
