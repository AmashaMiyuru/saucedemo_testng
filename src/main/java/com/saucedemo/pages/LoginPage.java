package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By txtUsername = By.id("user-name");
    private final By txtPassword = By.id("password");
    private final By btnLogin = By.id("login-button");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void login(String username, String password) {
        WebElement userElem = wait.until(ExpectedConditions.visibilityOfElementLocated(txtUsername));
        userElem.clear();
        userElem.sendKeys(username);

        WebElement passElem = wait.until(ExpectedConditions.visibilityOfElementLocated(txtPassword));
        passElem.clear();
        passElem.sendKeys(password);

        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
        loginBtn.click();

        // Wait until navigation to inventory page completes
        wait.until(ExpectedConditions.urlContains("inventory.html"));
    }
}