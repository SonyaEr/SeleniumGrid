package org.example;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.io.InputStream;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class Factory {
    public static WebDriver getDriver() {

        String browser = null;
        String screenResolution = null;

        try (InputStream input = Factory.class.getClassLoader().getResourceAsStream("browser.properties");) {
            Properties prop = new Properties();
            prop.load(input);
            browser = prop.getProperty("browser");
            screenResolution = prop.getProperty("screenResolution");
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert browser != null;
        if (browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
            return new ChromeDriver();
        }
        if (browser.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\geckodriver.exe");
            FirefoxOptions options = new FirefoxOptions();
            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("browser.helperApps.neverAsk.openFile", "application/octet-stream");
            profile.setAcceptUntrustedCertificates(true);
            profile.setAssumeUntrustedCertificateIssuer(false);
            profile.setPreference("geo.enabled", true);
            profile.setPreference("geo.prompt.testing", true);
            profile.setPreference("geo.prompt.testing.allow", true);
            options.setProfile(profile);
            return new FirefoxDriver(options);
        }
        if (browser.equals("edge")) {
            System.setProperty("webdriver.edge.driver", "src\\main\\resources\\msedgedriver.exe");
            return new EdgeDriver();
        }
        throw new IllegalArgumentException("No supported driver");
    }

    public static RemoteWebDriver initializeBrowser(String browser) throws MalformedURLException {
        DesiredCapabilities dc = new DesiredCapabilities();
        RemoteWebDriver driver = null;

        if (browser.equals("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--ignore-certificate-errors");
            chromeOptions.addArguments("--disable-popup-blocking");
            dc.setBrowserName("chrome");

        } else if (browser.equalsIgnoreCase("firefox")) {
            dc.setBrowserName("firefox");

        } else if (browser.equalsIgnoreCase("edge")) {
            dc.setBrowserName("MicrosoftEdge");
        }
        driver = new RemoteWebDriver(new URL("http://localhost:4444"), dc);
        return driver;
    }
}
