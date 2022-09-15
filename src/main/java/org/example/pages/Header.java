package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Header extends BasePage {

    @FindBy(css = "button.button-reset.search-btn")
    private WebElement searchButton;

    @FindBy(id = ("input_search"))
    private WebElement searchInput;

    @FindBy(xpath ="//div[contains(@class,'header-bottom__cart')]/parent::a")
    private WebElement ShoppingCart;

    public Header(WebDriver driver) {
        super(driver);
    }

    public void searchByKeyword(final String keyword) {
        searchInput.sendKeys(keyword);
        searchButton.click();
    }
    public void clickOnShoppingCart() {
        ShoppingCart.click();
    }

}
