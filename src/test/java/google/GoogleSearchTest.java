package google;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;
import utils.BaseTest;
import utils.Tools;

/**
 * Created by Kos on 7/17/17.
 */
public class GoogleSearchTest extends BaseTest {

    @DataProvider(name = "searches_request_provider")
    public Object[][] provider () throws Exception {
        return new String[][]{{"42"}, {"Google sucks"}};
    }


    @Test(dataProvider="searches_request_provider")
    public void Search(String searchRequest){

        GoogleSearchPage searchPage = new GoogleSearchPage();

        searchPage.open();

        searchPage.performSearch(searchRequest);

//        Assert.fail("fuk");
    }

}
