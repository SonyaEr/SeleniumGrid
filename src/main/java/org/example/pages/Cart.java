package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Cart  extends BasePage {

    @FindBy(xpath = "//div[@class='popup__window']")
    private  WebElement cartPopup;

    @FindBy(xpath = "//div[contains(@data-location, 'checkout')]")
    private  WebElement buttonCheckout;

    public Cart(WebDriver driver) {
        super(driver);
    }
    public void clickButtonCheckout() {
        buttonCheckout.click();
    }
    public WebElement getCartPopup() {
       return cartPopup;
    }

}
