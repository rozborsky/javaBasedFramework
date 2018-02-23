package pages.sinoptik;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import pages.BasePage;

import java.util.List;

public class SinoptikMainPage extends BasePage {
    private By cityInput = By.id("search_city");
    private String cityFullData;
    private By map = By.xpath("//div[@id='topMenu']//a[contains(text(), ' погода на мапі ') ]");

    public void setCity(String city) {
        findElement(cityInput).sendKeys(city);
        findElement(cityInput).sendKeys(Keys.ENTER);
    }

    public int getNumberOfCitiesInList(String city) {
        findElement(cityInput).sendKeys(city);

        return findElements(By.xpath("//div[@id='ac_b']//li")).size();
    }

    public void chooseCityFromList(String city, int numberCityInList) {
        List<WebElement> cities = getCitiesList(city);
        cityFullData = cities.get(numberCityInList).getText();

        cities.get(numberCityInList).click();
    }


    public void chooseCityInRegionAndDistrict(String city, String region, String district) {
        List<WebElement> cities = getCitiesList(city);
        for (int i = 0; i < cities.size(); i++) {
            WebElement currentCity =  cities.get(i);
            cityFullData = currentCity.getText();

            if (getSearchedCityRegion().equals(region)
                    && getSearchedCityDistrict().equals(district)) {
                currentCity.click();
                break;
            }
        }
    }


    private List<WebElement> getCitiesList(String city) {
        findElement(cityInput).sendKeys(city);

        return findElements(By.xpath("//div[@id='ac_b']//li"));
    }


    public boolean isCitiesIdentical() {
        if (
                searchedCityName(cityFullData).equals(getCurrentCityName())
                && getSearchedCityRegion().equals(getCurrentCityRegion())
                && getSearchedCityDistrict().equals(getCurrentCityDistrict())) {
            return true;
        }

        return false;
    }


    private String searchedCityName(String searchedCity) {
        String cityName = searchedCity.split(", ")[0];
        return cityName.substring(0, cityName.length() - 2);
    }


    private String getSearchedCityRegion() {
        String region = cityFullData.split(", ")[1];

        return region.split(" ")[0];
    }

    private String getSearchedCityDistrict() {
        String region = cityFullData.split(", ")[2];

        return region.split(" ")[0];
    }


    private String getCurrentCityName() {
        String cityInH1 = findElement(By.xpath("//div[@id='header']//h1//strong")).getText();
        String[] subStr = cityInH1.split(" ");
        String cityName = subStr[subStr.length - 1];
        String cityNameWithoutEnd = cityName.substring(0, cityName.length() - 2);

        return cityNameWithoutEnd;
    }


    private String getCurrentCityRegion() {
        return getCurrentLocation()[0].split(" ")[0];
    }


    private String getCurrentCityDistrict() {
        return getCurrentLocation()[1].split(" ")[0];
    }

    private String[] getCurrentLocation() {
        String currentLocation = findElement(By.xpath("//div[@id='header']//div[@class='currentRegion']")).getText();

        return currentLocation.split(", ");
    }

    public void showMap() {
        findElement(map).click();
    }
}
