package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

public class LoginTests extends BaseTest {

    @Test
    public void validLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertEquals(productsPage.getTitle(), "Products");
    }

    @Test
    public void invalidPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "wrong_password");

        Assert.assertTrue(loginPage.getErrorMessage()
                .contains("Username and password do not match"));
    }

    @Test
    public void lockedOutUser() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("locked_out_user", "secret_sauce");

        Assert.assertTrue(loginPage.getErrorMessage()
                .contains("this user has been locked out"));
    }

    @Test
    public void emptyFields() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("", "");

        Assert.assertTrue(loginPage.getErrorMessage()
                .contains("Username is required"));
    }
}