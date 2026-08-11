package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InventoryPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By btnAddBackpack = By.id("add-to-cart-sauce-labs-backpack");
    private final By btnCart = By.className("shopping_cart_link");
    private final By lblCartBadge = By.className("shopping_cart_badge");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void addBackpackToCart() {
        WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(btnAddBackpack));
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addBtn);
        } catch (Exception e) {
            addBtn.click();
        }
        wait.until(ExpectedConditions.textToBePresentInElementLocated(lblCartBadge, "1"));
    }

    public String getCartBadgeCount() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(lblCartBadge)).getText();
    }

    public void goToCart() {
        WebElement cartBtn = wait.until(ExpectedConditions.elementToBeClickable(btnCart));
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cartBtn);
        } catch (Exception e) {
            cartBtn.click();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(btnCheckoutOnCartPage()));
    }

    private By btnCheckoutOnCartPage() {
        return By.id("checkout");
    }
}