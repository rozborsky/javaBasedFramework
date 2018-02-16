package joyreactor;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.Joyreactor;
import utils.BaseTest;
import utils.ReporterManager;

import java.util.List;

public class JoyreactorTest extends BaseTest{

    @DataProvider(name = "searches_request_provider")
    public Object[][] provider () throws Exception {
        return new String[][]{{"cat"}};
    }


    @Test(dataProvider="searches_request_provider")
    public void Search(String searchRequest){

        Joyreactor joyreactor = new Joyreactor();
        joyreactor.open();

        joyreactor.performSearchByXpath(searchRequest);
        WebElement firstPost = joyreactor.getFirstPost();
        List<WebElement> elementsByXpath = joyreactor.getTagsByXpath(firstPost);

        List<WebElement> elementsByCss = joyreactor.getTagsByCss(firstPost);

        joyreactor.addTagsToReport(elementsByXpath);

        Assert.assertEquals(elementsByXpath, elementsByCss);
    }
}
