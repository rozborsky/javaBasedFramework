package pages.uz;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

import java.util.List;

/**
 * Created by Admin on 10/20/2017.
 */
public class UzResultPage extends BasePage{
   private By trainsTable = By.xpath("//div[@id='train-list']//table[@class='train-table']");

   public int getNumberOfTrains() {
       WebElement table = findElement(trainsTable);

       return table.findElements(By.xpath(".//tr")).size();
   }
}
