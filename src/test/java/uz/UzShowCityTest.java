package uz;

import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.uz.UzPage;
import utils.BaseTest;

/**
 * Created by Kos on 7/17/17.
 */
public class UzShowCityTest extends BaseTest {

    @DataProvider(name = "searches_request_provider")
    public Object[][] provider () throws Exception {
        return new String[][]{{"vinn", "Вінниця"}};
    }


    @Test(dataProvider="searches_request_provider")
    public void Search(String destinationPoint, String city){
        UzPage uzPage = new UzPage();
        uzPage.open();
        uzPage.setDepartureCity(destinationPoint);
        Assert.assertTrue(uzPage.isShowCityName(city));
    }
}
