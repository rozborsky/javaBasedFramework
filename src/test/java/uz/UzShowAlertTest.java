package uz;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.uz.UzPage;
import utils.BaseTest;

/**
 * Created by Kos on 7/17/17.
 */
public class UzShowAlertTest extends BaseTest {

    @DataProvider(name = "searches_request_provider")
    public Object[][] provider () throws Exception {
        return new String[][]{{"vinn", "Вінниця", "Введіть пункт призначення"}};
    }


    @Test(dataProvider="searches_request_provider")
    public void Search(String destinationPoint, String city, String expectedMessage){
        UzPage uzPage = new UzPage();
        uzPage.open();
        uzPage.setDepartureCity(destinationPoint);
        uzPage.chooseDepartureCityFromList(city);
        uzPage.clickSearchButton();
        Assert.assertEquals(uzPage.getAlertMessage(), expectedMessage);
    }
}
