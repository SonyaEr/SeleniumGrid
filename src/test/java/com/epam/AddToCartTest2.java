package com.epam;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AddToCartTest2 extends BaseTest {

    private final int WAITING_TIME = 90;
    private final String SEARCH_KEYWORD = "samsung";
    private final String TITLE_PAGE = "Вхід та реєстрація";

    @Test(priority = 1)
    public void checkCartSignIn() {
        getHomePage().waitForPageLoadComplete(WAITING_TIME);
        getHeader().clickOnShoppingCart();
        getHomePage().waitForPageLoadComplete(WAITING_TIME);
        getHomePage().waitForVisibilityOfElement(5, getSamsungPage().getCartPopup());
        getCart().clickLogin();
        assertEquals(getLoginPage().getTitle(), TITLE_PAGE);
    }


}

