package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By btnCheckout = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickCheckout() {
        WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(btnCheckout));
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkoutBtn);
        } catch (Exception e) {
            checkoutBtn.click();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));
    }
}