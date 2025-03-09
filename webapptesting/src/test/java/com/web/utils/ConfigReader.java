package com.web.utils;

import org.testng.TestNG;

public class ConfigReader {
    public static void main(String[] args) {
        TestNG testng = new TestNG();
        testng.setTestClasses(new Class[]{
                com.web.tests.LoginTest.class,
                com.web.tests.ProductTest.class
        });
        testng.run();
    }
}
