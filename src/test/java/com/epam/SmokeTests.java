package com.epam;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SmokeTests extends BaseTest {

    private final int WAITING_TIME = 120;

    @Test(dataProvider="reviewData", priority = 1)
    public void checkAddReview(String nameCom, String mail, String message, int star) {
        getHomePage().waitForPageLoadComplete(WAITING_TIME);
        getHomePage().waitForAjaxToComplete(WAITING_TIME);
        assertTrue(getHomePage().isReviewLinkVisible());
        getHomePage().clickGetReview();
        getHomePage().waitForPageLoadComplete(WAITING_TIME);
        assertTrue(getReviewPage().isAddCommentVisible());
        getReviewPage().clickOnAddComment();
        getReviewPage().waitForVisibilityOfElement(5, getReviewPage().getReviewPopup());
        assertTrue(getReviewPage().isCommentNameFieldVisible());
        assertTrue(getReviewPage().isCommentMailFieldVisible());
        assertTrue(getReviewPage().isCommentMessageFieldVisible());
        assertTrue(getReviewPage().isStarsVisible(star));
        assertTrue(getReviewPage().isSubmitCommentVisible());
        getReviewPage().setCommentName(nameCom);
        getReviewPage().setCommentMail(mail);
        getReviewPage().setCommentMessage(message);
        getReviewPage().clickOnStar(star);
        assertTrue(getReviewPage().isSelectedStarVisible(star));
    }

    @Test(dataProvider="SearchData", priority = 2)
    public void searchInput(String word) {
        getHomePage().waitForPageLoadComplete(WAITING_TIME);
        getHomePage().waitForAjaxToComplete(WAITING_TIME);
        assertTrue(getHeader().isFormSearchVisible());
        getHeader().searchByKeyword(word);
        getHeader().pressSpacebar();
        getHomePage().actionsMoveToElement(getHeader().getFirstSearchSection());
        int result =  getHeader().getSearchSectionByKeyword(word);
        assertEquals(result, getHeader().getSearchSection());

    }

    @Test(priority = 3)
    public void checkRemoveProductAfterCreateCart() {
        getHomePage().waitForPageLoadComplete(WAITING_TIME);
        getHomePage().waitForAjaxToComplete(WAITING_TIME);
        assertTrue(getHomePage().isImgProductVisible());
        getHomePage().clickOnImgProduct();
        getProductPage().waitForPageLoadComplete(WAITING_TIME);
        assertTrue(getProductPage().isButtonBuyProductVisible());
        getProductPage().clickButtonBuyProduct();
        getProductPage().waitForVisibilityOfElement(5, getCart().getCartPopup());
        getCart().clickButtonCheckout();
        getCheckoutPage().waitForPageLoadComplete(WAITING_TIME);
        assertTrue(getCheckoutPage().isButtonRemoveVisible());
        String currentUrlOld = getCheckoutPage().getCurrentUrl();
        getCheckoutPage().clickOnButtonRemove();
        getCheckoutPage().waitForEmptyPage(currentUrlOld);
        String currentUrlNew = getCheckoutPage().getCurrentUrl();
        assertEquals(currentUrlNew, BaseTest.get()+"#empty");
    }

    @DataProvider(name="reviewData")
    public Object[][] getDataFromDataprovider() {
        return new Object[][]
                {
                        {"Sonya", "sofia@gmail.com", "отзыв",1},
        {"Настя", "n@ukr.net","11111",5},
                             {"Женя", "1g@gmail.com","!_1Dh",3}
                };
    }

    @DataProvider(name = "SearchData")
    public Object[][] dataProvFunc(){
        return new Object[][]{
                   {"eye"}, {"cream"}
        };
    }
}

