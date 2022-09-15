package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(className = "page-title")
    private WebElement title;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return title.getText();
    }

}
