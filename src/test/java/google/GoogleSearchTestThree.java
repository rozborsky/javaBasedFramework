package google;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.GoogleSearchPage;
import pages.GoogleSearchPageThree;
import utils.BaseTest;

/**
 * Created by Kos on 7/17/17.
 */
public class GoogleSearchTestThree extends BaseTest {

    @DataProvider(name = "searches_request_provider")
    public Object[][] provider () throws Exception {
        return new String[][]{{"42"}};
    }


    @Test(dataProvider="searches_request_provider")
    public void Search(String searchRequest){

        GoogleSearchPageThree searchPage = new GoogleSearchPageThree();
        searchPage.open();
        searchPage.performSearch(searchRequest);
    }
}
