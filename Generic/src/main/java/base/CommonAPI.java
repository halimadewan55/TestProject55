package base;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CommonAPI {
    WebDriver driver = null;

    @BeforeMethod
    public void setUP(String url) {
        //driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\HALIMA\\IdeaProjects\\MavenTesting\\browser\\chromedriver.exe");

        //Create prefs map to store all preferences
        Map<String, Object> prefs = new HashMap<String, Object>();

//Put this into prefs map to switch off browser notification
        prefs.put("profile.default_content_setting_values.notifications", 2);

        //Create chrome options to set this prefs
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefsss", prefs);


        //Now initialize chrome driver with chrome options which will switch off this browser notification on the chrome browser
         driver = new ChromeDriver(options);

    }
    @AfterMethod
    public void cleanUp()
    {
        driver.close();
    }

    //Type
    public void typeOnCss(String locator, String value)
    {
        driver.findElement(By.cssSelector(locator)).sendKeys(value);
    }
    public void typeOnId(String locator,String value){
        driver.findElement(By.id(locator)).sendKeys(value);
    }
    public void typeOnElement(String locator,String value)
    {
        try{
            driver.findElement(By.cssSelector(locator)).sendKeys(value);
        }catch (Exception ex1){
            try{
                System.out.println("First Attempt was not successful");
                driver.findElement(By.name(locator)).sendKeys(value);
            }catch (Exception ex2){
                try {
                    System.out.println("Second Attempt was not successful");
                    driver.findElement(By.xpath(locator)).sendKeys(value);
                }catch (Exception ex3){
                    System.out.println("Second Attempt was not successful");
                    driver.findElement(By.id(locator)).sendKeys(value);

                }
            }
        }
    }


    public void typeOnElementNEnter(String locator, String value){
        try {
            driver.findElement(By.cssSelector(locator)).sendKeys(value, Keys.ENTER);
        }catch(Exception ex1) {
            try{
                System.out.println("First Attempt was not successful");
                driver.findElement(By.name(locator)).sendKeys(value, Keys.ENTER);
            }catch(Exception ex2) {
                try {
                    System.out.println("Second Attempt was not successful");
                    driver.findElement(By.xpath(locator)).sendKeys(value, Keys.ENTER);
                } catch (Exception ex3) {
                    System.out.println("Third Attempt was not successful");
                    driver.findElement(By.id(locator)).sendKeys(value, Keys.ENTER);
                }
            }
        }
    }


}

