package weatherComparison;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.GoogleSearchPage;
import pages.GoogleWeatherPage;
import rest.RestYahoo;
import utils.BaseTest;

/**
 * Created by Kos on 7/17/17.
 */
public class WeatherComparisonTest extends BaseTest {

    @DataProvider(name = "searches_request_provider")
    public Object[][] provider () throws Exception {
        return new String[][]{{"погода вінниця"}};
    }


    @Test(dataProvider="searches_request_provider")
    public void Search(String searchRequest){
        GoogleSearchPage searchPage = new GoogleSearchPage();
        searchPage.open();
        searchPage.performSearch(searchRequest);

        GoogleWeatherPage googleWeatherPage = new GoogleWeatherPage();
        String googleTemperature = googleWeatherPage.getTemperature();

        RestYahoo restYahoo = new RestYahoo();
        String yahooTemperature = restYahoo.getTemp();

        Assert.assertEquals(googleTemperature, yahooTemperature, "Temperature is different");
    }
}
