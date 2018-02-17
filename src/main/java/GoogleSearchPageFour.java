import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import pages.BasePage;

/**
 * Created by Admin on 10/20/2017.
 */
public class GoogleSearchPageFour extends BasePage{

    public GoogleSearchPageFour(){
        pageURL = "/";
    }

    By searchField = By.name("q");

    public void performSearch(String query){
        findElement(searchField).sendKeys(query);
        findElement(searchField).sendKeys(Keys.ENTER);
    }
}
