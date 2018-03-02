package pages.blablacar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.BasePage;

import java.util.List;

public class BlablacarMainPage extends BasePage {
    private By fromInput = By.id("search_from_name");
    private By toInput = By.id("search_to_name");
    private By dateInput = By.id("search_to_date");
    private By cityFromList = By.xpath("//form[@id='search-form']//div[contains(@class, 'js-trip-search-error-from-container')]//li/a");
    private By cityToList = By.xpath("//form[@id='search-form']//div[contains(@class, 'js-trip-search-error-to-container')]//li/a");
    private By findTripButton = By.xpath("//form[@id='search-form']//button[@type='submit']");
    private By currentDayInCalendar = By.xpath("//div[@id='ui-datepicker-div']//tbody//a");
    private By currentDayInput = By.id("search_to_date");
    private String currentDay;


    public void isPresentFromInput() {
        findElement(fromInput);
    }


    public void isPresentToInput() {
        findElement(toInput);
    }


    public void isPresentDateInput() {
        findElement(dateInput);
    }


    public void setFromCity(String city) {
        findElement(fromInput).sendKeys(city);
        setCityFromList(cityFromList, city);
    }


    public void setToCity(String city) {
        findElement(toInput).sendKeys(city);
        setCityFromList(cityToList, city);
    }


    public void findDateInput() {
        findElement(dateInput);
    }


    public void setCurrentDay() {
        findElement(dateInput).click();
        findElement(currentDayInCalendar).click();
        currentDay = findElement(currentDayInput).getAttribute("value");
    }


    public String getCurrentDay() {
        return currentDay;
    }


    public void findTrip(String cityFrom, String cityTo) {
        setToCity(cityTo);
        setFromCity(cityFrom);
        findElement(findTripButton).click();
    }


    public void findTripToday(String cityFrom, String cityTo) {
        setToCity(cityTo);
        setFromCity(cityFrom);
        setCurrentDay();
        findElement(findTripButton).click();
    }


    public void setCityFromList(By cityFromList, String city) {
        List<WebElement> links = findElements(cityFromList);
        for (int i = 0; i < links.size(); i++) {
            String fullDescriptionCity = links.get(i).getText();
            String cityName = fullDescriptionCity.substring(0, fullDescriptionCity.indexOf(","));
            if (cityName.toLowerCase().equals(city.toLowerCase())) {
                links.get(i).click();
                break;
            }
        }
    }
}
