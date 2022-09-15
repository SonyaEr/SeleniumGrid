package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.openqa.selenium.By.xpath;

public class HomePage extends BasePage {

    @FindBy(xpath = "//div[@class='menu-lvl first-level']/ul/li[@class='parent js_sidebar-item']")
    private  List<WebElement> menu;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement>  getMenu() {
        return menu;
    }

    public String getHrefMenu(int sectionMenu) {
        return driver.findElement(By.xpath("(//div[@class='menu-lvl first-level']/ul/li[@class='parent js_sidebar-item']/a)[" + sectionMenu + "+1]")).getAttribute("href");
    }

    public List<WebElement> getFirstSubMenu(int sectionMenu) {
        return  driver.findElements(xpath("(//div[@class='menu-lvl second-level next-level js_next-level'])[" + sectionMenu + "+1]/ul/li[@class='parent js_sidebar-item']"));
    }

    public String getHrefFirstSubMenu(int sectionMenu, int sectionFirstSubMenu) {
        return driver.findElement(By.xpath("((//div[@class='menu-lvl second-level next-level js_next-level'])[" + sectionMenu + "+1]/ul/li[@class='parent js_sidebar-item']/a)[" + sectionFirstSubMenu + "+1]")).getAttribute("href");
    }
    public List<WebElement> getSecondSubMenu(int sectionMenu, int sectionFirstSubMenu) {
        return  driver.findElements(xpath("((//ul[@class='sidebar-list'])[" + sectionMenu + "+1]/li[@class='parent js_sidebar-item'])[" + sectionFirstSubMenu + "+1]//li[@class='single-hover-block']/a"));
    }
    public String getHrefSecondSubMenu(int sectionMenu, int sectionFirstSubMenu,int sectionSecondSubMenu ) {
        return driver.findElement(xpath("(((//ul[@class='sidebar-list'])[" + sectionMenu + "+1]/li[@class='parent js_sidebar-item'])[" + sectionFirstSubMenu + "+1]//li[@class='single-hover-block']/a)[" +sectionSecondSubMenu+ " +1]")).getAttribute("href");
    }

    public ArrayList<String> checkHrefSet(Set<String> set){
        String url;
        HttpURLConnection huc;
        int respCode;

        Iterator<String> it = set.iterator();
        ArrayList<String> invalid = new ArrayList<>();
        while (it.hasNext()) {
            url = it.next();
            if (url == null || url.isEmpty()) {
                continue;
            }
            try {
                huc = (HttpURLConnection) (new URL(url).openConnection());
                huc.setRequestMethod("HEAD");
                huc.connect();
                respCode = huc.getResponseCode();
                if (respCode >= 400) {
                    invalid.add(url);
                }
            } catch (IOException e) {
                invalid.add(url);
            }
        }
        return invalid;
    }
}