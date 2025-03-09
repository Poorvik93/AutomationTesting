package com.web.tests;

import com.web.base.BaseTest;
import com.web.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void testLoginAndFetchProduct() {
        try {
            // Set username and password
            loginPage.enterUsername("standard_user");
            loginPage.enterPassword("secret_sauce");

            // Perform login and get the ProductPage
            ProductPage productPage = loginPage.login("standard_user", "secret_sauce");

            // Check if login succeeded
            if (productPage == null) {
                String errorMessage = loginPage.getErrorMessage(); // Fetching error message on failure
                Assert.fail("Login failed! Error message: " + errorMessage);
            }
            Assert.assertNotNull(productPage, "Product Page is null even after login.");

        } catch (Exception e) {
            Assert.fail("Test failed due to an unexpected exception: " + e.getMessage());
        }
    }
}
