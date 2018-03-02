package pages.googleTranslate;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import pages.BasePage;

public class AuthorisationPage extends BasePage {
    private By inputEmail = By.id("identifierId");
    private By inputpassword = By.xpath("//div[@id='password']//input");

    public void entryIntoTheSystem(String email, String password) {
        setEmail(email);
        setPassword(password);
    }


    private void setEmail(String email) {
        findElement(inputEmail).sendKeys(email);
        findElement(inputEmail).sendKeys(Keys.ENTER);
    }


    private void setPassword(String password) {
        findElement(inputpassword).sendKeys(password);
        findElement(inputpassword).sendKeys(Keys.ENTER);
    }
}
