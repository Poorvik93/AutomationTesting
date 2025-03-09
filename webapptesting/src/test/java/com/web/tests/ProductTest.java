package com.web.tests;

import com.web.base.BaseTest;
import com.web.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ProductTest extends BaseTest {

    @Test
    public void testProductDetails() {
        try {
            // Step 1: Login to the application
            ProductPage productPage = loginPage.login("standard_user", "secret_sauce");

            // Step 2: Check if login failed
            if (loginPage.isLoginFailed()) {
                String error = loginPage.getLoginErrorMessage();
                System.err.println("Login failed with error: " + error);
                Assert.fail("Login failed with error: " + error);
            }

            // Step 3: Verify product header is displayed
            assertTrue(productPage.isProductHeaderDisplayed(), "\nProduct header is not displayed\n");
            System.out.println("Product header is displayed successfully.");

            // Step 4: Verify page title to ensure we are on the Product Page
            boolean onProductPage = productPage.isOnProductPage();
            Assert.assertTrue(onProductPage, "Not on the Product Page based on page title!");
            System.out.println("Verified we are on the Product Page.");

            // Step 5: Save product details
            productPage.saveProductDetails();

            // Step 6: Add first product to the cart
            productPage.addFirstProductToCart();

            // Step 7: Verify if product is present in the cart
            boolean productInCart = productPage.isProductInCart();
            Assert.assertTrue(productInCart, "Product not found in the cart after adding it.");

            // Step 8: Logout
            productPage.logOut();

        } catch (AssertionError ae) {
            // Handle assertion failures (expected issues)
            System.err.println("Assertion failed: " + ae.getMessage());
            Assert.fail("Test failed due to assertion failure: " + ae.getMessage());

        } catch (Exception e) {
            // Handle unexpected exceptions (errors, crashes, etc.)
            System.err.println("Exception occurred during test execution: " + e.getMessage());
            e.printStackTrace();
            Assert.fail("Test failed due to an unexpected exception: " + e.getMessage());
        }
    }
}
