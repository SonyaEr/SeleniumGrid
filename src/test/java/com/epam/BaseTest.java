package com.epam;

import org.example.Factory;
import org.example.pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static org.example.Factory.getDriver;

public class BaseTest {

    private static WebDriver driver = null;


    private static final String AVIC_URL = "https://avic.ua/";

    @BeforeMethod
    public void testsSetUp() {
        driver = getDriver();
        driver.manage().window().maximize();
        driver.get(AVIC_URL);
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }

    public SamsungPage getSamsungPage() {
        return new SamsungPage(driver);
    }

    public HomePage getHomePage() {
        return new HomePage(driver);
    }

    public Header getHeader() {
        return new Header(driver);
    }

    public Cart getCart() {
        return new Cart(driver);
    }

    public LoginPage getLoginPage() {
        return new LoginPage(driver);
    }


}
