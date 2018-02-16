package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class UkrnetMainPage extends BasePage {

    public void openSection(String sectionTitle) {
        findElement(By.xpath("//a[contains( text(),'" + sectionTitle + "')]")).click();
    }
}