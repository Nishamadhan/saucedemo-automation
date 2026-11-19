package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductsPage;

public class CheckoutTests extends BaseTest {

    private CheckoutPage goToCheckoutPage() {
        new LoginPage(driver).login("standard_user", "secret_sauce");
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addBackpack();
        productsPage.openCart();
        new CartPage(driver).clickCheckout();
        return new CheckoutPage(driver);
    }

    @Test
    public void completeCheckout() {
        CheckoutPage checkoutPage = goToCheckoutPage();
        checkoutPage.enterDetails("Nisha", "M", "605001");
        checkoutPage.clickContinue();
        checkoutPage.clickFinish();

        Assert.assertEquals(checkoutPage.getConfirmationMessage(),
                "Thank you for your order!");
    }

    @Test
    public void checkoutWithMissingFirstName() {
        CheckoutPage checkoutPage = goToCheckoutPage();
        checkoutPage.enterDetails("", "M", "605001");
        checkoutPage.clickContinue();

        Assert.assertTrue(checkoutPage.getErrorMessage()
                .contains("First Name is required"));
    }
}
