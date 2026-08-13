package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SauceDemoTests extends BaseTest {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @BeforeMethod
    public void pageSetup() {
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    @Test
    public void testLogin() {
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
    }

    @Test
    public void testAddToCart() {
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage.addBackpackToCart();
        Assert.assertEquals(inventoryPage.getCartBadgeCount(), "1");
    }

    @Test
    public void testMissingPostalCodeValidation() {
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage.addBackpackToCart();
        inventoryPage.goToCart();
        cartPage.clickCheckout();
        checkoutPage.enterInformation("John", "Doe", "");
        checkoutPage.clickContinue();
        Assert.assertEquals(checkoutPage.getErrorMessage(), "Error: Postal Code is required");
    }

    @Test
    public void testMissingFirstNameValidation() {
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage.addBackpackToCart();
        inventoryPage.goToCart();
        cartPage.clickCheckout();
        checkoutPage.enterInformation("", "Doe", "12345");
        checkoutPage.clickContinue();
        Assert.assertEquals(checkoutPage.getErrorMessage(), "Error: First Name is required");
    }

    @Test
    public void testEndToEndCheckout() {
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage.addBackpackToCart();
        inventoryPage.goToCart();
        cartPage.clickCheckout();
        checkoutPage.enterInformation("John", "Doe", "12345");
        checkoutPage.clickContinue();
        checkoutPage.clickFinish();
        Assert.assertEquals(checkoutPage.getConfirmationHeader(), "Thank you for your order!");
    }
}