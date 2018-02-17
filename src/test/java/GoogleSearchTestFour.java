import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.GoogleSearchPage;
import utils.BaseTest;

/**
 * Created by Kos on 7/17/17.
 */
public class GoogleSearchTestFour extends BaseTest {

    @DataProvider(name = "searches_request_provider")
    public Object[][] provider () throws Exception {
        return new String[][]{{"42"}, {"Google sucks"}};
    }


    @Test(dataProvider="searches_request_provider")
    public void Search(String searchRequest){

        GoogleSearchPageFour searchPage = new GoogleSearchPageFour();

        searchPage.open();

        searchPage.performSearch(searchRequest);
        Assert.assertEquals(true, false, searchRequest + "-------------test four-------------");
    }
}
