package com.epam;

import org.example.Factory;
import org.example.pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
    private static final String MAKEUP_URL = "https://makeup.com.ua/";
    public static String get() {
        return MAKEUP_URL;
    }

    @BeforeMethod
    @Parameters(value = {"browser"})
    public void testsSetUp(@Optional("firefox") String browser) throws MalformedURLException {
        driver.set(new RemoteWebDriver(new URL("http://192.168.0.107:4444/wd/hub"), Factory.getCapabilities(browser)));
        getDriver().manage().window().maximize();
        getDriver().get(MAKEUP_URL);
        try {
            driver.get().findElement(By.xpath("//div[@class='popup active animate']//div[contains(@class,'close-icon')]")).click();

        } catch (NoSuchElementException e) {
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.get().close();
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    public HomePage getHomePage() {
        return new HomePage(driver.get());
    }

    public Header getHeader() {
        return new Header(driver.get());
    }

    public ReviewPage getReviewPage() {return new ReviewPage(getDriver());}

    public Cart getCart() {return new Cart(driver.get());}

    public ProductPage getProductPage() {return new ProductPage(getDriver());}

    public CheckoutPage getCheckoutPage() {return new CheckoutPage(getDriver());}
}
