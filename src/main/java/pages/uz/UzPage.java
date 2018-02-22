package pages.uz;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 10/20/2017.
 */
public class UzPage extends BasePage{
    private By frame = By.xpath("//iframe[@src='https://booking.uz.gov.ua/widget/']");
    private By departureCityInput = By.xpath("//div[@id='station_from']//input");
    private By destinationCityInput = By.xpath("//div[@id='station_to']//input");
    private String departureCityList = "//div[@id='station_from']//ul[@id='ui-id-1']//li";
    private String destinationCityList = "//div[@id='station_to']//ul[@id='ui-id-2']//li";
    private By cityNames = By.xpath(departureCityList);
    private By searchButton = By.tagName("button");


    public void setDepartureCity(String departurePoint) {
        switchToFrameOrderTickets();
        findElement(departureCityInput).sendKeys(departurePoint);
    }


    public void chooseDepartureCityFromList(String city) {
        findElement(By.xpath(departureCityList + "[contains(text(), '" + city + "')]")).click();
    }


    public void chooseDestinationCityFromList(String city) {
        findElement(By.xpath(destinationCityList + "[contains(text(), '" + city + "')]")).click();
    }


    public void setDestinationCity(String city) {
        findElement(destinationCityInput).sendKeys(city);
    }


    public void clickSearchButton() {
        findElement(searchButton).click();
    }


    public String getAlertMessage() {
        Alert alert = driver().switchTo().alert();

        return alert.getText().replaceAll("\\p{Cntrl}", "");
    }


    public boolean isShowCityName(String city) {
        List<WebElement> citys =  findElements(cityNames);
        for (int i = 0; i < citys.size(); i++) {
            if(citys.get(i).getText().toLowerCase().contains(city.toLowerCase())) {
                return true;
            }
        }
        return false;
    }


    private void switchToFrameOrderTickets() {
        waitForAllIfraimes();
        driver().switchTo().frame(findElement(frame));
    }


    private void waitForAllIfraimes() {
        WebDriverWait wait = new WebDriverWait(driver(), 15);
        wait.until(new ExpectedCondition<Boolean>() {
                       public Boolean apply(WebDriver driver) {
                           return findElements(By.tagName("iframe")).size() == 3;
                       }
                   });

    }


    public void switchToResultTab() {
        ArrayList<String> tabs = new ArrayList<>(driver().getWindowHandles());
        driver().switchTo().window(tabs.get(1));
    }
}
