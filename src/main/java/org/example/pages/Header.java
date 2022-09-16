package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Header extends BasePage {

    @FindBy(id = ("search-input"))
    private WebElement searchInput;

    @FindBy(xpath = "//form [contains(@action, 'search')]")
    private WebElement formSearch;

    @FindBy(xpath = "//li[@class='search-result__list scrolling']")
    private WebElement dropDown;

    @FindBy(xpath = "//li[@class='search-result__item']")
    private List<WebElement> searchSections;

    public Header(WebDriver driver) {
        super(driver);
    }

    public void searchByKeyword(final String keyword) {
        searchInput.sendKeys(keyword);

    }
    public void pressSpacebar() {
        searchInput.sendKeys(Keys.SPACE);
    }

    public int getSearchSectionByKeyword(String word) {
      String upperWord = word.substring(0, 1).toUpperCase() + word.substring(1);
        return driver.findElements
                (By.xpath("//div[@class='search-result']//div[@class='search-result__data']/div[contains(text(), '" + word + "') or contains(text(),'" + upperWord+"')]/parent::div")).size();
    }
    public int getSearchSection() {
       return searchSections.size();
    }

    public WebElement getFirstSearchSection() {
        return dropDown;
    }

    public boolean isFormSearchVisible(){
        return formSearch.isDisplayed();
    }

}
