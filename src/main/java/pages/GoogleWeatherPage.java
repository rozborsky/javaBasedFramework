package pages;

import org.openqa.selenium.By;

/**
 * Created by Admin on 10/20/2017.
 */
public class GoogleWeatherPage extends BasePage{
    private By temperatureWalueFahrenheit = By.xpath("//span[@id='wob_ttm']");
    private By chooseFahrenheitValue = By.xpath("//span[@aria-label='°Fahrenheit']");


    public String getTemperature() {
        String res = "";
        findElement(chooseFahrenheitValue).click();
        res =  findElement(temperatureWalueFahrenheit).getText();

        return res;
    }
}
