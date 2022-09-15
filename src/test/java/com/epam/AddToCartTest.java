package com.epam;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AddToCartTest extends BaseTest {

    private final int WAITING_TIME = 90;
    private final String SEARCH_KEYWORD = "samsung";
    private final String TITLE_PAGE = "Вхід та реєстрація";

    @Test(priority = 1)
    public void checkAddToOrder() {
        getHomePage().waitForPageLoadComplete(WAITING_TIME);
        getHeader().searchByKeyword(SEARCH_KEYWORD);
        getHomePage().waitForPageLoadComplete(WAITING_TIME);
        getSamsungPage().clickOnAddToCartButton(1);
        getSamsungPage().waitForVisibilityOfElement(3, getSamsungPage().getCartPopup());
        getCart().clickOnAddToOrder(1);
        getHomePage().waitForAjaxToComplete(WAITING_TIME);
        getCart().waitForPresenceOfElementLocated(5, getCart().getLocator(1, 2));
        String tittle = getCart().getTitleAddItem(1);
        WebElement el = getCart().getTitleItem(tittle);
        assertTrue(el.isDisplayed());
    }

    @Test(priority = 2)
    public void checkAmountAfterAddQuantity() {
        getHomePage().waitForPageLoadComplete(WAITING_TIME);
        getHeader().searchByKeyword(SEARCH_KEYWORD);
        getHomePage().waitForPageLoadComplete(WAITING_TIME);
        getSamsungPage().clickOnAddToCartButton(1);
        getSamsungPage().waitForVisibilityOfElement(3, getSamsungPage().getCartPopup());
        String totalPriceOld = getCart().getTotalPrice();
        getCart().clickOnAddToOrder(1);
        getCart().waitForElementToBeClickable(5, getCart().getLocator(2, 1));
        getCart().clickOnPlus(1);
        getCart().waitForChangeTotalPrice(10, totalPriceOld);
        List<WebElement> prices = getCart().getItemPrices();
        int numberNew = 0;
        for (int i = 0; i < prices.size(); i++) {
            String[] part = prices.get(i).getText().split(" ");
            if (part.length != 2) {
                throw new IllegalArgumentException();
            }
            int totalH = Integer.parseInt(part[0]);
            int count = getCart().getCountOfItem(i);
            numberNew = numberNew + totalH * count;
        }
        String totalPrice = getCart().getTotalPrice();
        String[] part2 = totalPrice.split(" ");
        if (part2.length != 2) {
            throw new IllegalArgumentException();
        }
        int number = Integer.parseInt(part2[0]);
        assertEquals(numberNew, number);
    }

    @Test(priority = 3)
    public void checkCartSignIn() {
        getHomePage().waitForPageLoadComplete(WAITING_TIME);
        getHeader().clickOnShoppingCart();
        getHomePage().waitForPageLoadComplete(WAITING_TIME);
        getHomePage().waitForVisibilityOfElement(5, getSamsungPage().getCartPopup());
        getCart().clickLogin();
        assertEquals(getLoginPage().getTitle(), TITLE_PAGE);
    }


}

