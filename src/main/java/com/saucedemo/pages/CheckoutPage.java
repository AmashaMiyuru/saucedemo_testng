package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By txtFirstName = By.id("first-name");
    private final By txtLastName = By.id("last-name");
    private final By txtPostalCode = By.id("postal-code");
    private final By btnContinue = By.id("continue");
    private final By btnFinish = By.id("finish");
    private final By lblErrorMessage = By.cssSelector("[data-test='error']");
    private final By lblCompleteHeader = By.className("complete-header");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterInformation(String firstName, String lastName, String postalCode) {
        WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(txtFirstName));
        setInputValue(firstNameField, firstName);

        WebElement lastNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(txtLastName));
        setInputValue(lastNameField, lastName);

        WebElement postalCodeField = wait.until(ExpectedConditions.visibilityOfElementLocated(txtPostalCode));
        setInputValue(postalCodeField, postalCode);
    }

    public void clickContinue() {
        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(btnContinue));
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueBtn);
        } catch (Exception e) {
            continueBtn.click();
        }
    }

    public void clickFinish() {
        WebElement finishBtn = wait.until(ExpectedConditions.elementToBeClickable(btnFinish));
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", finishBtn);
        } catch (Exception e) {
            finishBtn.click();
        }
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='error']"))).getText();
    }

    public String getConfirmationHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(lblCompleteHeader)).getText();
    }

    private void setInputValue(WebElement field, String value) {
        String normalizedValue = value == null ? "" : value;
        ((JavascriptExecutor) driver).executeScript(
            "const valueSetter = Object.getOwnPropertyDescriptor(HTMLInputElement.prototype, 'value').set;" +
                "valueSetter.call(arguments[0], arguments[1]);" +
                        "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
                        "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
                field,
                normalizedValue);
    }
}