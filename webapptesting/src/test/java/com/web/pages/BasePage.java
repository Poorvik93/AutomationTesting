package com.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    /**
     * Constructor initializes the driver and wait.
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Waits for the element to be visible and returns it.
     */
    protected WebElement find(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Clicks on the element after waiting for it to be clickable.
     */
    protected void click(By locator) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        } catch (Exception e) {
            System.err.println("Error clicking element: " + locator.toString());
            throw e;
        }
    }

    /**
     * Clears the field and sets the provided text.
     *
     */
    protected void setText(By locator, String text) {
        try {
            WebElement element = find(locator);
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            System.err.println("Error setting text on element: " + locator.toString());
            throw e;
        }
    }

    /**
     * Returns the text from the given element.
     */
    protected String getText(By locator) {
        try {
            return find(locator).getText();
        } catch (Exception e) {
            System.err.println("Error getting text from element: " + locator.toString());
            throw e;
        }
    }

    /**
     * Returns the current page title.
     */
    public String getPageTitle() {
        try {
            return driver.getTitle();
        } catch (Exception e) {
            System.err.println("Error retrieving page title.");
            throw e;
        }
    }

    /**
     * Returns the current URL of the page.
     */
    public String getCurrentUrl() {
        try {
            return driver.getCurrentUrl();
        } catch (Exception e) {
            System.err.println("Error retrieving current URL.");
            throw e;
        }
    }

}
