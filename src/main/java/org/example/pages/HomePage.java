package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(xpath = "(//div[@class='simple-slider-list__link']//div[contains(@class, 'reviews')]/a)[1]")
    private  WebElement reviewLink;

    @FindBy(xpath = "//div[@class='simple-slider-list__link']/a")
    private  WebElement imgProduct;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickGetReview() {
        reviewLink.click();
    }

    public void clickOnImgProduct() {
        imgProduct.click();
    }

    public boolean isImgProductVisible(){
        return imgProduct.isDisplayed();
    }

    public boolean isReviewLinkVisible(){
        return reviewLink.isDisplayed();
    }


}