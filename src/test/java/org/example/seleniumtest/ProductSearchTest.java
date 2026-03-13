package org.example.seleniumtest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ProductSearchTest {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        try {
            // Step 1: Open website
            driver.get("https://automationexercise.com");
            if (driver.getTitle().contains("Automation Exercise")) {
                System.out.println("Test Case 1: Website opened successfully - PASSED");
            } else {
                System.out.println("Test Case 1: Website not opened - FAILED");
            }

            // Step 2: Navigate to Products page
            driver.findElement(By.xpath("//a[@href='/products']")).click();
            if (driver.getCurrentUrl().contains("/products")) {
                System.out.println("Test Case 2: Navigated to Products page - PASSED");
            } else {
                System.out.println("Test Case 2: Failed to navigate to Products page - FAILED");
            }

            // Step 3: Enter search term
            driver.findElement(By.id("search_product")).sendKeys("Tshirt");
            String enteredText = driver.findElement(By.id("search_product")).getAttribute("value");
            if (enteredText.equals("Tshirt")) {
                System.out.println("Test Case 3: Search term entered correctly - PASSED");
            } else {
                System.out.println("Test Case 3: Search term not entered - FAILED");
            }

            // Step 4: Click search button
            driver.findElement(By.id("submit_search")).click();
            if (driver.getCurrentUrl().contains("search")) {
                System.out.println("Test Case 4: Search button clicked successfully - PASSED");
            } else {
                System.out.println("Test Case 4: Search button click failed - FAILED");
            }

            // Step 5: Verify search results
            String result = driver.findElement(By.xpath("//h2[contains(text(),'Searched Products')]")).getText();
            if (result.contains("Searched")) {
                System.out.println("Test Case 5: Search functionality working correctly - PASSED");
            } else {
                System.out.println("Test Case 5: Search functionality failed - FAILED");
            }

        } catch (Exception e) {
            System.out.println("Unexpected error occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
