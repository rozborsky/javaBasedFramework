package uz;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.uz.UzPage;
import pages.uz.UzResultPage;
import utils.BaseTest;

/**
 * Created by Kos on 7/17/17.
 */
public class UzCheckNumberOfTrainsTest extends BaseTest {

    @DataProvider(name = "searches_request_provider")
    public Object[][] provider () throws Exception {
        return new String[][]{{"vinn", "Вінниця", "Київ"}};
    }


    @Test(dataProvider="searches_request_provider")
    public void Search(String destinationPoint, String departureCity, String destinationCity){
        UzPage uzPage = new UzPage();
        uzPage.open();
        uzPage.setDepartureCity(destinationPoint);
        uzPage.chooseDepartureCityFromList(departureCity);
        uzPage.setDestinationCity(destinationCity);
        uzPage.chooseDestinationCityFromList(destinationCity);
        uzPage.clickSearchButton();
        uzPage.switchToResultTab();

        UzResultPage resultPage = new UzResultPage();
        Assert.assertTrue(resultPage.getNumberOfTrains() > 1);
    }
}
