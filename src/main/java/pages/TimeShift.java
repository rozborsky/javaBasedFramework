package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class TimeShift extends BasePage {

    By loginField = By.id("inputLogin");
    By passwordField = By.id("inputPassword");
    By submit = By.tagName("button");

    public void signIn(String login, String password) {
        findElement(loginField).sendKeys(login);
        findElement(passwordField).sendKeys(password);
        findElement(submit).click();
    }

    public String getTotalTime() {
        return findElement(By.xpath("//*[@class='week-total']")).findElement(By.tagName("span")).getText();
    }
}
