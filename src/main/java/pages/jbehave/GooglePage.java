package pages.jbehave;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Tools;

import java.util.List;


public class GooglePage {
    static WebDriver driver = getDriver();
    By searchField = By.name("q");

    static private WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "C:/selenium/chromedriver.exe");
        return new ChromeDriver();
    }


    public void openGoogle() {
        driver.get("https://www.google.com");
    }

    public void closeGoogle() {
        driver = null;
    }


    public void performSearch(String query){
        findElement(searchField).sendKeys(query);
        findElement(searchField).sendKeys(Keys.ENTER);
    }


    public static WebElement findElement(By element, int... timeout) {
        int timeoutForFindElement = timeout.length < 1 ? 30 : timeout[0];
        waitForPageToLoad();
        try {
            //synchronize();
            (new WebDriverWait(driver, timeoutForFindElement))
                    .until(ExpectedConditions.visibilityOfElementLocated(element));
            return driver.findElement(element);
        } catch (Exception e) {

            throw new RuntimeException("Failure finding element");
        }
    }

    public static void waitForPageToLoad(){
        sleepFor(10); // todo fixme
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {

            public Boolean apply(WebDriver driver)
            {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState")
                        .equals("complete");
            }

        };

        Wait<WebDriver> wait = new WebDriverWait(driver, 15);

        try
        {
            wait.until(expectation);
        } catch (Exception error)
        {

        }
    }

    public static void sleepFor(int timeout){
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
        }
    }


}
