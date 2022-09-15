package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

import static org.openqa.selenium.By.xpath;

public class Cart  extends BasePage {

    @FindBy(className = "btn-add")
    private List<WebElement> addToOrder;

    @FindBy(xpath = "//div[contains(@class,'item-total')]/span[contains(@class,'prise')]")
    private WebElement totalPrice;

    @FindBy(xpath = "//div[contains(@class,'total-h')]/span[contains(@class,'prise')]")
    private  List<WebElement> itemPrices;

    @FindBy(xpath ="//div[@id='js_cart']/descendant::a[contains(@href,'sign-in')]")
    private WebElement login;

    @Override
    public By getLocator(int i, int item) {
        switch (i) {
            case 1: return By.xpath("(//div[@class='item'])["+item+"]");
            case 2: return By.xpath("(//span[contains(@class,'plus')])["+item+"]");
        }
        return null;
    }

    public Cart(WebDriver driver) {
        super(driver);
    }

    public void clickOnAddToOrder(int item) {
        addToOrder.get(item).click();
    }

    public void clickOnPlus(int item) {
        driver.findElement(xpath("(//span[contains(@class,'plus')])["+item+"]")).click();
    }

    public void clickLogin() {
        login.click();
    }

    public String getTitleAddItem(int item) {
        return  driver.findElement(By.xpath("((//a[@class='btn-add'])["+item+"]/following::a)[2]")).getText();
    }

    public WebElement getTitleItem(String title) {
        return driver.findElement(By.xpath("//div[@class='item']//span[contains(text(),'" + title + "')]"));
    }

    public String getTotalPrice() {
        return totalPrice.getText();
    }

    public List<WebElement> getItemPrices() {
        return itemPrices;
    }

    public int getCountOfItem(int item) {
        return Integer.parseInt(driver.findElement(xpath("(//input[@class='js-changeAmount count'])[" + item + "+1]")).getAttribute("value"));
    }

    public void waitForChangeTotalPrice(int seconds, String totalPriceOld) {
        (new WebDriverWait(driver, Duration.ofSeconds(seconds))).until((ExpectedCondition<WebElement>) webDriver -> {
            if (!Objects.equals(totalPriceOld, totalPrice.getText())) {
                return totalPrice;
            }
            return null;
        });
    }
}
