package com.web.pages;

import com.web.utils.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * This class represents the Product Page of the web application.
 */
public class ProductPage extends BasePage {

    // Locators for web elements on the Product page
    private By productsHeader = By.xpath("//span[text()='Products']");
    private By productNames = By.cssSelector(".inventory_item_name");
    private By productPrices = By.cssSelector(".inventory_item_price");
    private By addToCartButton = By.cssSelector(".btn_inventory");
    private By cartIcon = By.className("shopping_cart_link");
    private By cartProductNames = By.className("inventory_item_name");
    private By logoutButton = By.id("react-burger-menu-btn");
    private By logoutLink = By.id("logout_sidebar_link");

    /**
     * Constructor to initialize the ProductPage with WebDriver.
     * @param driver WebDriver instance passed from test or base class.
     */
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Verifies if the Products header is displayed on the page.
     * @return true if Products header is displayed, false otherwise.
     */
    public boolean isProductHeaderDisplayed() {
        return find(productsHeader).isDisplayed();
    }

    /**
     * Returns the title of the current page.
     * @return page title as String.
     */
    public String getPageTitle() {
        String title = driver.getTitle();
        System.out.println("Current Page Title: " + title);
        return title;
    }

    /**
     * Checks if the user is on the Product Page by verifying the title.
     * @return true if the page title matches expected, false otherwise.
     */
    public boolean isOnProductPage() {
        String expectedTitle = "Swag Labs";  // Example title (update if needed)
        return getPageTitle().equals(expectedTitle);
    }

    /**
     * Gets the name of the first product listed on the page.
     * @return first product name as String.
     */
    public String getFirstProductName() {
        return getText(productNames);
    }

    /**
     * Gets the price of the first product listed on the page.
     * @return first product price as String.
     */
    public String getFirstProductPrice() {
        return getText(productPrices);
    }

    /**
     * Saves the details (name and price) of the first product to a file.
     */
    public void saveProductDetails() {
        String name = getFirstProductName();
        String price = getFirstProductPrice();
        FileUtils.saveProductDetails(name, price);
    }

    /**
     * Adds the first product to the cart by clicking its "Add to Cart" button.
     * Throws an exception if no buttons are found or not clickable.
     */
    //// For select the list of product and sent the Send one to cart
//    public void addFirstProductToCart() {
//        List<WebElement> buttons = driver.findElements(addToCartButton);
//        if (!buttons.isEmpty() && buttons.get(0).isEnabled()) {
//            buttons.get(0).click();
//            System.out.println("First product added to cart successfully.");
//        } else {
//            throw new RuntimeException("Add to Cart button is not available");
//        }
//    }
    public void addFirstProductToCart() {
        try {
            // Find only the first "Add to Cart" button
            WebElement addButton = driver.findElement(addToCartButton);

            if (addButton.isDisplayed() && addButton.isEnabled()) {
                addButton.click();
                System.out.println("First product added to cart successfully.");
            } else {
                throw new RuntimeException("Add to Cart button is not available or not enabled");
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to add product to cart: " + e.getMessage());
        }
    }


    /**
     * Verifies whether the first product is present in the cart.
     * @return true if the product is found in the cart, false otherwise.
     */
    public boolean isProductInCart() {
        // Get product name before clicking on cart
        String expectedProductName = getFirstProductName();

        // Navigate to the cart page
        click(cartIcon);
        System.out.println("Navigated to Cart Page.");

        // Get all product names listed in the cart
        List<WebElement> cartProducts = driver.findElements(cartProductNames);

        // Check if any product in cart matches the expected product name
        boolean found = cartProducts.stream()
                .anyMatch(p -> p.getText().equals(expectedProductName));

        if (found) {
            System.out.println("Product found in cart: " + expectedProductName);
        } else {
            System.err.println("Product NOT found in cart: " + expectedProductName);
        }

        return found;
    }

    /**
     * Logs out from the application by clicking the logout menu button and then the logout link.
     */
    public void logOut() {
        try {
            click(logoutButton);   // Open the side menu
            click(logoutLink);     // Click logout link in the menu
            System.out.println("Logged out successfully.");
        } catch (Exception e) {
            System.err.println("Error during logout: " + e.getMessage());
            throw e;
        }
    }
}

