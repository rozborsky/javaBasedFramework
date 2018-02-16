package pages;

import org.openqa.selenium.By;

public class JqueryuiDatepickerPage extends BasePage{

    public JqueryuiDatepickerPage(){
        pageURL = "/datepicker";
    }

    public void choseDate(String textOnElement) {

        driver().switchTo().frame(findElement(By.className("demo-frame")));
        findElement(By.id("datepicker")).click();
        findElement(By.xpath("//a[@class='ui-state-default' and contains(text(), '" + textOnElement + "')]")).click();
        driver().switchTo().defaultContent();
    }
}