package org.evershop.test;

import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;
    @BeforeTest
    public void setupDriver(){
        this.driver = new ChromeDriver();
    }
    @AfterTest
    public void quitDriver() {
        // Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
        this.driver.quit();
    }
}
