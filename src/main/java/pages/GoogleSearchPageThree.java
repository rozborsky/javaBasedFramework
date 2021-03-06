package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

/**
 * Created by Admin on 10/20/2017.
 */
public class GoogleSearchPageThree extends BasePage{

    public GoogleSearchPageThree(){
        pageURL = "/";
    }

    By searchField = By.name("q");

    public void performSearch(String query){
        findElement(searchField).sendKeys(query);
        findElement(searchField).sendKeys(Keys.ENTER);
    }
}
