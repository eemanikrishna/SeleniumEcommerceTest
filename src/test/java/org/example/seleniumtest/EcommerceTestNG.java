package org.example.seleniumtest;

import org.testng.annotations.*;
import static org.testng.Assert.*;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class EcommerceTestNG {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testEcommerceFlow() {
        driver.get("https://www.saucedemo.com");
        assertTrue(driver.getTitle().contains("Swag Labs"), "Website should open successfully");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        assertTrue(driver.getCurrentUrl().contains("inventory.html"), "Login should be successful");

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        String cartBadge = driver.findElement(By.className("shopping_cart_badge")).getText();
        assertEquals("1", cartBadge, "Cart badge should show 1 item");

        driver.findElement(By.className("shopping_cart_link")).click();
        assertTrue(driver.getCurrentUrl().contains("cart.html"), "Cart page should open");

        String product = driver.findElement(By.className("inventory_item_name")).getText();
        assertTrue(product.contains("Backpack"), "Cart should contain Backpack");
    }
}
