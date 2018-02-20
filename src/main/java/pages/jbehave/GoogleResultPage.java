package pages.jbehave;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class GoogleResultPage extends GooglePage{
    private String keyWord;
    private By title = By.xpath("//div[@class='srg']//h3[@class='r']//a");
    private By link = By.xpath("//div[@class='srg']//cite[@class='_Rm']");
    private By text = By.xpath("//div[@class='srg']//span[@class='st']");
    private By results = By.xpath("//div[@class='rc']");
    private int numberREsults;
    private int numberREsultsInTitles;
    private int numberREsultsIlLinks;
    private int numberREsultsInTexts;


    public GoogleResultPage(String keyWord) {
        this.keyWord = keyWord;
    }


    public boolean isWordPresentInAllTitles() {
        List<WebElement> titles = findElements(title);
        numberREsults = findElements(results).size();
        numberREsultsInTitles = titles.size();

        return isWordPresentInAllMembersOfCollection(titles);
    }


    public boolean isWordPresentInAllLinks() {
        List<WebElement> links = findElements(link);
        numberREsultsIlLinks = links.size();

        return isWordPresentInAllMembersOfCollection(links);
    }


    public boolean isWordPresentInAllTexts() {
        List<WebElement> texts = findElements(text);

        numberREsultsInTexts = texts.size();
        return isWordPresentInAllMembersOfCollection(texts);
    }


    public int getNumberREsults() {
        return numberREsults;
    }


    public int getNumberREsultsInTitles() {
        return numberREsults;
    }


    public int getNumberREsultsIlLinks() {
        return numberREsults;
    }


    public int getNumberREsultsInTexts() {
        return numberREsults;
    }


    private boolean isWordPresentInAllMembersOfCollection(List<WebElement> list){
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).getText().toLowerCase().contains(keyWord)) {
                return false;
            }
        }

        return true;
    }


    public static List<WebElement> findElements(By element, int... timeout) {
        int timeoutForFindElement = timeout.length < 1 ? 15 : timeout[0];
        waitForPageToLoad();
        try {
            (new WebDriverWait(driver, timeoutForFindElement))
                    .until(ExpectedConditions.presenceOfElementLocated(element));
            return driver.findElements(element);
        } catch (Exception e) {
            throw new RuntimeException("Failure finding elements");
        }
    }
}
