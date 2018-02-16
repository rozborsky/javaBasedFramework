package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class JqueryuiListPage extends BasePage{
    public JqueryuiListPage(){
        pageURL = "/sortable";
    }

    String targetElementText = "";

    public void moveElement() {
        String movingElementText = "Item 1";
        targetElementText = "Item 4";

        driver().switchTo().frame(0);
        WebElement reorderredElement = findElement(By.xpath("//li[contains (@class, 'ui-state-default') and contains(text(), '" + movingElementText + "')]"));
        WebElement targetElement = findElement(By.xpath("//li[contains (@class, 'ui-state-default') and contains(text(), '" + targetElementText + "')]"));

        Actions action = new Actions(driver());
        action.dragAndDropBy(reorderredElement, 5, Integer.valueOf(targetElement.getLocation().y) + 10).build().perform();
        driver().switchTo().defaultContent();
    }
}