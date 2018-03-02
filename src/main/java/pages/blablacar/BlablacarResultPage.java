package pages.blablacar;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.BasePage;

import java.util.concurrent.TimeUnit;

public class BlablacarResultPage extends BasePage {
    private By numberTrips = By.xpath("//div[@class='RegularSearch']/div[@class='u-clearfix']/h1");
    private By cityInputFrom = By.id("search_from_name");
    private By cityInputTo = By.id("search_to_name");
    private By dateInput = By.xpath("//div[@id='search-facets']//div[@class='date-facet-input']//input[contains(@class, 'input-date')]");
    private By sortByPriceButton = By.xpath("//div[@id='search-results']//div[@class='trip-search-sort']//a[contains(@class, 'order-price')]");
    private By sortByDateButton = By.xpath("//div[@id='search-results']//div[@class='trip-search-sort']//a[contains(@class, 'order-date')]");
    private By price = By.xpath("//div[@id='search-results']//ul[@class='trip-search-results']//div[contains(@class, 'price')]/strong/span");
    private By dateTrip = By.xpath("//div[@id='search-results']//ul[@class='trip-search-results']//li[contains(@class, 'trip')]//h3[contains(@class, 'time')]");
    private By minTimeSlider = By.xpath("//div[@id='search-facets']//div[contains(@class, 'hour-facet')]//span[contains(@class, 'ui-slider-handle')]");
    private By maxTimeSlider = By.xpath("//div[@id='search-facets']//div[contains(@class, 'hour-facet')]//span[contains(@class, 'ui-slider-handle')]");
    private By minTime = By.xpath("//div[@id='search-facets']//div[contains(@class, 'hour-facet')]//span[contains(@class, 'minValueDisplay')]");
    private By maxTime = By.xpath("//div[@id='search-facets']//div[contains(@class, 'hour-facet')]//span[contains(@class, 'maxValueDisplay')]");


    public int getNumberTrips() {
        String text = findElement(numberTrips).getText();

        return Integer.parseInt(text.substring(text.indexOf(":") + 2, text.length()));
    }


    public String getFromCityName() {
        return getCityName(cityInputFrom);
    }


    public String getToCityName() {
        return getCityName(cityInputTo);
    }


    public String getCityName(By cityInput) {
        String foolDescription = findElement(cityInput).getAttribute("value");

        return foolDescription.substring(0, foolDescription.indexOf(",")).toLowerCase();//dp1519683275905 dp1519683402291
    }


    public String getDate() {
        return findElement(dateInput).getAttribute("value");
    }


    private void sortByPrise(By button) {
        WebElement buttonsort = findElement(sortByPriceButton);
        buttonsort.click();
        buttonsort.findElement(button).click();
    }


    private void sortByDecreasePrice() {
        sortByPrise(By.xpath(".//ancestor::div[contains(@class, 'btn-group')]//li[@class='asc']/a"));
    }


    private void sortByIncreasePrice() {
        sortByPrise(By.xpath(".//ancestor::div[contains(@class, 'btn-group')]//li[@class='desc']/a"));
    }


    public String getLowestPrice() {
        sortByDecreasePrice();
        return findElement(price).getText();
    }

    public String getHighestPrice() {
        sortByIncreasePrice();
        return findElement(price).getText();
    }


    public void getFirstTrip() {
        System.out.println(findElement(dateTrip).getText()); //report
    }


    public void getLastTrip() {
        sortByDecreaseDate();
        System.out.println(findElement(dateTrip).getText()); //report
    }


    private void sortByDecreaseDate() {
        WebElement buttonsort = findElement(sortByDateButton);
        buttonsort.click();
        buttonsort.findElement(By.xpath(".//ancestor::div[contains(@class, 'btn-group')]//li[@class='desc']/a")).click();
    }


    public void setTimeRange(String from, String to) {
        setMinTime(from);
        setMaxTime(to);
    }


    private void setMinTime(String from) {
        while(!getTime(minTime).equals(from)) {
            try
            {
                moveMinSlider(minTimeSlider);
            }
            catch(StaleElementReferenceException e )
            {
                //do nothing, try again
            }
        }
    }


    private void setMaxTime(String to) {
        while(!getTime(maxTime).equals(to)) {
            try
            {
                moveMaxSlider(maxTimeSlider);
            }
            catch(StaleElementReferenceException e )
            {
                //do nothing, try again
            }
        }
    }


    private void moveMinSlider(By slider) {
        Actions builder = new Actions(driver());
        try
        {
            builder.dragAndDropBy(findElements(slider).get(0), 12, 0).build().perform();
        }
        catch(StaleElementReferenceException e )
        {
            //do nothing, try again
        }
    }


    private void moveMaxSlider(By slider) {
        Actions builder = new Actions(driver());
        try
        {
            builder.dragAndDropBy(findElements(slider).get(1), -2, 0).build().perform();
        }
        catch(StaleElementReferenceException e )
        {
            //do nothing, try again
        }
    }


    private String getTime(By time) {
        String visibleTime;
        try
        {
            visibleTime = findElement(time).getText();
        }
        catch(StaleElementReferenceException e )
        {
            driver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            visibleTime = findElement(time).getText();
        }

        return visibleTime.substring(0, visibleTime.length() - 4);
    }


    public void setCostRange(String from, String to) {

    }
}
//selenium grid
//sause labs