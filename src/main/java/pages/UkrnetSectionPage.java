package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class UkrnetSectionPage extends BasePage {
    By newsSelector = By.xpath("//section[@class='im']");
    By newsTitle = By.xpath(".//a[@class='im-tl_a']");
    By time = By.xpath(".//time[@class='im-tm']");

    public List<WebElement> getNews(String keyWord) {
        List<WebElement> news = findElements(newsSelector);
        news = filterNews(news, keyWord);
        reportLinks(news);

        return news;
    }

    private List<WebElement> filterNews(List<WebElement> news, String keyWord) {
        List<WebElement> updatedNews= new ArrayList<>();

        for (int i = 0; i < news.size(); i++) {
            WebElement currentNews = news.get(i);
            if (isNewsWasAddedtooday(currentNews) &&
                 currentNews.findElement(newsTitle).getText().toLowerCase().contains(keyWord.toLowerCase())) {
                updatedNews.add(currentNews);
            } //else System.out.println(currentNews.findElement(time).getText() + " " + currentNews.findElement(newsTitle).getText());
        }

        return updatedNews;
    }

    private void reportLinks(List<WebElement> news) {
        String newsString = "";

        for (int i = 0; i < news.size(); i++) {
            newsString = news.get(i).findElement(newsTitle).getText() +
                    "<br>" +
                    news.get(i).findElement(newsTitle).getAttribute("href");
            reporter.info(newsString);
        }
    }

    private boolean isNewsWasAddedtooday(WebElement webElement) {
        return webElement.findElement(time).getText().contains(":");
    }
}
