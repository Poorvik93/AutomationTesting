package com.web.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//public class FileUtils {
//
//    // Method to save product details to a file
//    public static void saveProductDetails(String productName, String productPrice) {
//        String filePath = "productDetails.txt"; // Define file path
//
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
//            writer.write("Product Name: " + productName);
//            writer.newLine();
//            writer.write("Product Price: " + productPrice);
//            writer.newLine();
//            System.out.println(" Product details saved to: " + filePath);
//        } catch (IOException e) {
//            System.err.println("Error writing to file: " + e.getMessage());
//        }
//    }
//}


public class FileUtils {
	  private static final String FILE_PATH = "product_details.txt"; // Define file path

	    public static void saveProductDetails(String name, String price) {
	        File file = new File(FILE_PATH);
	        
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
	            writer.write("Product Name: " + name + ", Price: " + price);
	            writer.newLine();
	            System.out.println("Product details saved successfully in " + FILE_PATH);
	        } catch (IOException e) {
	            System.out.println("Error writing to file: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }
}