package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class CheckoutPage extends BasePage {

    @FindBy(xpath = "//div[@class='product__button-remove']")
    private WebElement buttonRemove;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public boolean isButtonRemoveVisible() {
        return buttonRemove.isDisplayed();
    }

    public void clickOnButtonRemove() {
        buttonRemove.click();
    }
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void waitForEmptyPage(String currentUrlOld) {
        (new WebDriverWait(driver, Duration.ofSeconds(8))).until(webDriver -> {
            return !Objects.equals(currentUrlOld, driver.getCurrentUrl());
        });
    }
}
