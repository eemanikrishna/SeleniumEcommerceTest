package org.example.seleniumtest;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class EcommerceTest {

    public static void main(String[] args) {

        ChromeDriver driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        try {
            // Open website
            driver.get("https://www.saucedemo.com");
            Thread.sleep(3000);
            if (driver.getTitle().contains("Swag Labs")) {
                System.out.println("Test Case 1: Website opened successfully - PASSED");
            } else {
                System.out.println("Test Case 1: Website not opened - FAILED");
            }

            // Login
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            Thread.sleep(3000);
            driver.findElement(By.id("login-button")).click();

            if (driver.getCurrentUrl().contains("inventory.html")) {
                System.out.println("Test Case 2: Login successful - PASSED");
            } else {
                System.out.println("Test Case 2: Login failed - FAILED");
            }

            // Add product to cart
            driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
            String cartBadge = driver.findElement(By.className("shopping_cart_badge")).getText();
            if (cartBadge.equals("1")) {
                System.out.println("Test Case 3: Product added to cart - PASSED");
            } else {
                System.out.println("Test Case 3: Product not added - FAILED");
            }
            Thread.sleep(3000);

            // Open cart
            driver.findElement(By.className("shopping_cart_link")).click();
            if (driver.getCurrentUrl().contains("cart.html")) {
                System.out.println("Test Case 4: Cart opened successfully - PASSED");
            } else {
                System.out.println("Test Case 4: Cart not opened - FAILED");
            }
            Thread.sleep(3000);

            // Verify product added
            String product = driver.findElement(By.className("inventory_item_name")).getText();
            if (product.contains("Backpack")) {
                System.out.println("Test Case 5: Correct product present in cart - PASSED");
            } else {
                System.out.println("Test Case 5: Product verification failed - FAILED");
            }
            Thread.sleep(3000);

        } catch (Exception e) {
            System.out.println("Unexpected error occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
