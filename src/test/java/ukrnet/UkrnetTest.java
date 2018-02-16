package ukrnet;

import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.UkrnetMainPage;
import pages.UkrnetSectionPage;
import utils.BaseTest;

import java.util.List;

public class UkrnetTest extends BaseTest {

    @DataProvider(name = "searches_request_provider")
    public Object[][] provider () throws Exception {
        return new String[][]{{"Головне", "укр", "5"}};
    }


    @Test(dataProvider="searches_request_provider")
    public void Search(String sectionTitle, String word, String number){

        UkrnetMainPage ukrnetMainPage = new UkrnetMainPage();
        ukrnetMainPage.open();
        ukrnetMainPage.openSection(sectionTitle);

        UkrnetSectionPage ukrnetSectionPage = new UkrnetSectionPage();
        List<WebElement> newsLinks = ukrnetSectionPage.getNews(word);


//        Assert.assertEquals(newsLinks.size(), number);
    }
}
