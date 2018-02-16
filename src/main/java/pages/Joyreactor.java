package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Joyreactor extends BasePage {
    By searchField = By.xpath("//form[@id='searchform']/input[@name='q']");


    public void performSearchByXpath(String query){
        findElement(searchField).sendKeys(query);
        findElement(searchField).sendKeys(Keys.ENTER);
    }

    public WebElement getFirstPost() {
        return driver().findElement(By.className("postContainer"));
    }

    public List<WebElement> getTagsByXpath(WebElement post) {
        return  post.findElements(By.xpath(".//h2[@class='taglist']")).get(0).findElements(By.xpath("./b/a"));
    }

    public List<WebElement> getTagsByCss(WebElement post) {
        return post.findElements(By.cssSelector(".taglist>b>a"));
    }

    public void addTagsToReport(List<WebElement> elementsByXpath) {
        String tags = "";
        for (int i = 0; i < elementsByXpath.size(); i++) {
            tags += elementsByXpath.get(i).getText() + " ";
        }
        reporter.info("tags is: " + tags);
    }
}
