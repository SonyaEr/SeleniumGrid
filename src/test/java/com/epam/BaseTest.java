package com.epam;

import org.example.Factory;
import org.example.pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;

public class BaseTest {

    protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
    private static final String AVIC_URL = "https://avic.ua/";

    @BeforeMethod
    @Parameters(value = {"browser"})
    public void testsSetUp(@Optional("chrome") String browser) throws MalformedURLException {
        driver.set(Factory.initializeBrowser(browser));
        getDriver().manage().window().maximize();
        getDriver().get(AVIC_URL);
    }

    @AfterMethod
    public void tearDown() {
        driver.get().close();
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    public SamsungPage getSamsungPage() {
        return new SamsungPage(driver.get());
    }

    public HomePage getHomePage() {
        return new HomePage(driver.get());
    }

    public Header getHeader() {
        return new Header(driver.get());
    }

    public Cart getCart() {
        return new Cart(driver.get());
    }

    public LoginPage getLoginPage() {
        return new LoginPage(driver.get());
    }


}
