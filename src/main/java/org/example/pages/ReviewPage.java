package org.example.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.openqa.selenium.By.xpath;

public class ReviewPage extends BasePage{

    @FindBy(xpath = "//div[contains(@class, 'add-comment')]")
    private WebElement addComment;

    @FindBy(id ="comments-name")
    private WebElement commentNameField;

    @FindBy(id ="mail")
    private WebElement commentMailField;

    @FindBy(id ="comments-message")
    private WebElement commentMessageField;

    @FindBy(xpath = "//div[@id='rate']//ul/li")
    private List<WebElement> star;

    @FindBy(xpath = " //form[@data-popup='leave-comment'][contains(@class, 'active')]")
    private WebElement reviewPopup;

    @FindBy(xpath = " //form[@data-popup='leave-comment']//button[@type='submit']")
    private WebElement submitComment;

    public ReviewPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnAddComment() {
         addComment.click();
    }

    public void setCommentName(final String name) {
        commentNameField.sendKeys(name);
        commentNameField.sendKeys(Keys.ENTER);
    }
    public void setCommentMail(final String email) {
        commentMailField.sendKeys(email);
        commentMailField.sendKeys(Keys.ENTER);
    }
    public void setCommentMessage(final String message) {
        commentMessageField.sendKeys(message);
        commentMessageField.sendKeys(Keys.ENTER);
    }
    public WebElement getReviewPopup(){
        return  reviewPopup;
    }

    public boolean isAddCommentVisible(){
        return addComment.isDisplayed();
    }

    public boolean isSubmitCommentVisible(){
        return submitComment.isDisplayed();
    }

    public boolean isCommentNameFieldVisible(){
        return commentNameField.isDisplayed();
    }

    public boolean isCommentMailFieldVisible(){
        return commentMailField.isDisplayed();
    }

    public boolean isCommentMessageFieldVisible(){
        return commentMessageField.isDisplayed();
    }

    public boolean isStarsVisible(int num){
        return driver.findElement(xpath("//div[@id='rate']//ul/li[@title="+num+"]")).isDisplayed();
    }
    public boolean isSelectedStarVisible(int num){
        return driver.findElement(xpath("//div[@id='rate']//ul/li[@title="+num+"][contains(@class,'checked')]")).isDisplayed();

    }
    public void clickOnStar(int num) {
        driver.findElement(xpath("//div[@id='rate']//ul/li[@title="+num+"]")).click();
    }
}
