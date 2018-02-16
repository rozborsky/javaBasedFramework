package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class JqueryuiLogoPage extends BasePage{
    By logoXpath = By.xpath("//h2[@class='logo']");

    public void hideElement() {
        JavascriptExecutor js = (JavascriptExecutor) driver();
        WebElement element = findElement(logoXpath);
        js.executeScript("arguments[0].setAttribute('style', 'visibility: hidden')", element);
    }
}