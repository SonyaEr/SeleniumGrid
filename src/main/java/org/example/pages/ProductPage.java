package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {

    @FindBy(xpath = "//div[@class='product-item']//div[@class='button buy']")
    private  WebElement buttonBuyProduct;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public boolean isButtonBuyProductVisible(){
        return buttonBuyProduct.isDisplayed();
    }

    public void clickButtonBuyProduct() {
        buttonBuyProduct.click();
    }


}
