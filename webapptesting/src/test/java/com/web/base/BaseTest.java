package com.web.base;

import com.web.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected LoginPage loginPage;

    /**
     * Sets up the WebDriver and opens the application URL before running any test class.
     */
    @BeforeClass
    public void setUp() {
        try {
            // You can set the ChromeDriver path here if not already configured in system properties.
            // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

            driver = new ChromeDriver();                     
            driver.manage().window().maximize();             
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait

            driver.get("https://www.saucedemo.com");          
            System.out.println("Navigated to: " + driver.getCurrentUrl());

            loginPage = new LoginPage(driver);               

        } catch (Exception e) {
            System.err.println("Error during setup: " + e.getMessage());
            throw new RuntimeException("Error setting up WebDriver: " + e.getMessage(), e);
        }
    }

    /**
     * Quits the WebDriver session after all test cases in the class have run.
     */
    @AfterClass
    public void tearDown() {
        try {
            if (driver != null) {
                driver.quit();  
                System.out.println("Browser closed successfully.");
            }
        } catch (Exception e) {
            System.err.println("Error during teardown: " + e.getMessage());
            throw new RuntimeException("Error closing WebDriver: " + e.getMessage(), e);
        }
    }
}
