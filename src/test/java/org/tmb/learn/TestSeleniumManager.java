package org.tmb.learn;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class TestSeleniumManager {

    public static WebDriver driver;
    public String browserSelect ="edge";

    @Test
    public void testNewSelManager(){
        if(browserSelect.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if(browserSelect.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if(browserSelect.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else { System.out.println("Please provide proper browser name"); }
        driver.manage().window().maximize();
        driver.get("https://www.twitter.com");
        driver.quit();
    }
}
