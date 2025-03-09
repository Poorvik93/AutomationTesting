package com.web.pages;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

/**
 * Page Object Model for the Login Page of SauceDemo.
 * Provides methods to interact with login functionality and handle error states.
 */
public class LoginPage extends BasePage {

    // Locators
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.cssSelector("#login_button_container h3");

    /**
     * Constructor for LoginPage.
     *
     * @param driver WebDriver instance passed from test class
     */
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Enter username into username field.
     *
     * @param username Username to be entered
     */
    public void enterUsername(String username) {
        try {
            setText(usernameField, username);
            System.out.println("Entered username: " + username);
        } catch (NoSuchElementException e) {
            System.err.println("Username field not found: " + e.getMessage());
        } catch (WebDriverException e) {
            System.err.println("Error interacting with username field: " + e.getMessage());
        }
    }

    /**
     * Enter password into password field.
     *
     * @param password Password to be entered
     */
    public void enterPassword(String password) {
        try {
            setText(passwordField, password);
            System.out.println("Entered password.");
        } catch (NoSuchElementException e) {
            System.err.println("Password field not found: " + e.getMessage());
        } catch (WebDriverException e) {
            System.err.println("Error interacting with password field: " + e.getMessage());
        }
    }

    /**
     * Click the login button and return a ProductPage object if successful.
     *
     * @return ProductPage instance if login button is clicked, null otherwise.
     */
    public ProductPage clickLoginButton() {
        try {
            click(loginButton);
            System.out.println("Clicked login button.");
            return new ProductPage(driver);
        } catch (NoSuchElementException e) {
            System.err.println("Login button not found: " + e.getMessage());
        } catch (WebDriverException e) {
            System.err.println("Error clicking the login button: " + e.getMessage());
        }
        return null; // You can also throw a runtime exception here instead of returning null.
    }

    /**
     * Performs the login action: enters username and password, then clicks the login button.
     *
     * @param username Username
     * @param password Password
     * @return ProductPage object if login succeeds, otherwise null
     */
    public ProductPage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        return clickLoginButton();
    }

    /**
     * Check if login failed by verifying the presence of an error message.
     *
     * @return true if error message is displayed, false otherwise
     */
    public boolean isLoginFailed() {
        try {
            boolean errorDisplayed = find(errorMessage).isDisplayed();
            if (errorDisplayed) {
                System.out.println("Login failed error message displayed.");
            }
            return errorDisplayed;
        } catch (NoSuchElementException e) {
            return false; // No error displayed means no login failure
        } catch (WebDriverException e) {
            System.err.println("Error checking login failure: " + e.getMessage());
            return false;
        }
    }

    /**
     * Gets the login error message text.
     *
     * @return String error message if available, otherwise a default message
     */
    public String getLoginErrorMessage() {
        try {
            String errorText = find(errorMessage).getText();
            System.out.println("Login error message: " + errorText);
            return errorText;
        } catch (NoSuchElementException e) {
            System.err.println("Error message element not found: " + e.getMessage());
        } catch (WebDriverException e) {
            System.err.println("Error retrieving login error message: " + e.getMessage());
        }
        return "Unknown error occurred during login.";
    }

    /**
     * Alternative method to get the error message (redundant with getLoginErrorMessage).
     */
    
    public String getErrorMessage() {
        try {
            WebElement errorElement = find(errorMessage);
            String errorText = errorElement.getText();
            System.out.println("Error message displayed: " + errorText);
            return errorText;
        } catch (NoSuchElementException e) {
            System.err.println("Error message element not found: " + e.getMessage());
        } catch (WebDriverException e) {
            System.err.println("Error interacting with error message element: " + e.getMessage());
        }
        return "Error message not available.";
    }
}
