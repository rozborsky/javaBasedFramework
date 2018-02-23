package pages.sinoptik;

import org.openqa.selenium.By;
import pages.BasePage;

public class SinoptikNotFoundPage extends BasePage {

    public boolean foundMessage(String city) {
        String message = "Погоди по цьому пункту (" + city + "), на жаль, на сайті немає.";
        findElement(By.xpath("//div[@id='content']//div[@class='menu-city-static']//span[contains(text(), 'Погоди по цьому пункту ')]"));
//        findElement(By.xpath("//div[@id='content']//div[@class='menu-city-static']//span[contains(text(), '" + message + "')]"));

        return true;
    }
}
