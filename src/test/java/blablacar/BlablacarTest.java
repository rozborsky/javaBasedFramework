package blablacar;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.blablacar.BlablacarMainPage;
import pages.blablacar.BlablacarResultPage;
import utils.BaseTest;
import utils.ReporterManager;

/**
 * Created by Kos on 7/17/17.
 */
public class BlablacarTest extends BaseTest {
    static ReporterManager reporter = ReporterManager.Instance;

    @DataProvider(name = "from_to_date_presence_provider")
    public Object[][] fromToDatePresenceprovider () throws Exception {
        return new String[][]{{}};
    }

    @DataProvider(name = "from_to_provider")
    public Object[][] fromToProvider () throws Exception {
        return new String[][]{{"київ", "одесса", ""}};
    }

    @DataProvider(name = "from_to_today_provider")
    public Object[][] fromToTodayProvider () throws Exception {
        return new String[][]{{"київ", "вінниця"}};
    }

    @DataProvider(name = "cheapest_and_most_expensive_provider")
    public Object[][] cheapestAndMostExpensiveProvider () throws Exception {
        return new String[][]{{"харків", "львів"}};
    }

    @DataProvider(name = "first_and_last_date_provider")
    public Object[][] firstAndLastDateProvider () throws Exception {
        return new String[][]{{"Львів", "Харків"}};
    }

    @DataProvider(name = "time_and_cost_limitation_provider")
    public Object[][] timeAndCostLimitationProvider () throws Exception {
        return new String[][]{
                {"вінниця", "тернопіль", "9", "11", "500", "600"},
        {"вінниця", "тернопіль", "10", "11", "500", "600"},
        {"вінниця", "тернопіль", "11", "12", "500", "600"},
        {"вінниця", "тернопіль", "12", "13", "500", "600"},
        {"вінниця", "тернопіль", "9", "14", "500", "600"},
        {"вінниця", "тернопіль", "9", "15", "500", "600"},
        {"вінниця", "тернопіль", "9", "11", "500", "600"},
        {"вінниця", "тернопіль", "10", "12", "500", "600"},
        {"вінниця", "тернопіль", "9", "13", "500", "600"},
        {"вінниця", "тернопіль", "8", "14", "500", "600"},
        {"вінниця", "тернопіль", "22", "23", "500", "600"}

        };
    }

//
//    @Test(dataProvider="from_to_date_presence_provider")
//    public void fromToDatePresence(){
//        BlablacarMainPage blablacarmainPage = new BlablacarMainPage();
//        blablacarmainPage.open();
//
//        blablacarmainPage.isPresentFromInput();
//        blablacarmainPage.isPresentToInput();
//        blablacarmainPage.isPresentDateInput();
//    }
//
//
//    @Test(dataProvider="from_to_provider")
//    public void fromCityToCity(String cityFrom, String cityTo, String date){
//        BlablacarMainPage blablacarmainPage = new BlablacarMainPage();
//        blablacarmainPage.open();
//        blablacarmainPage.findTrip(cityFrom, cityTo);
//
//        BlablacarResultPage blablacarResultPage = new BlablacarResultPage();
//        blablacarResultPage.getNumberTrips();//always different
//    }
//
//
//    @Test(dataProvider="from_to_today_provider")
//    public void fromCityToCityToday(String cityFrom, String cityTo){
//        BlablacarMainPage blablacarmainPage = new BlablacarMainPage();
//        blablacarmainPage.open();
//        blablacarmainPage.findTripToday(cityFrom, cityTo);
//
//        BlablacarResultPage blablacarResultPage = new BlablacarResultPage();
//        Assert.assertEquals(blablacarResultPage.getFromCityName(), cityFrom.toLowerCase());
//        Assert.assertEquals(blablacarResultPage.getToCityName(), cityTo.toLowerCase());
//        Assert.assertEquals(blablacarResultPage.getDate(), blablacarmainPage.getCurrentDay());
//    }


//    @Test(dataProvider="cheapest_and_most_expensive_provider")
//    public void cheapestAndMostExpensive(String cityFrom, String cityTo){
//        BlablacarMainPage blablacarmainPage = new BlablacarMainPage();
//        blablacarmainPage.open();
//        blablacarmainPage.findTrip(cityFrom, cityTo);
//        BlablacarResultPage blablacarResultPage = new BlablacarResultPage();
//
//        reporter.info("<h3>lowest prise:</h3> " + blablacarResultPage.getLowestPrice());
//        reporter.info("<h3>highest prise:</h3> " + blablacarResultPage.getHighestPrice());
//    }


//    @Test(dataProvider="first_and_last_date_provider")
//    public void timeAndCostLimitation(String cityFrom, String cityTo){
//        BlablacarMainPage blablacarmainPage = new BlablacarMainPage();
//        blablacarmainPage.open();
//        blablacarmainPage.findTrip(cityFrom, cityTo);
//
//        BlablacarResultPage blablacarResultPage = new BlablacarResultPage();
//        blablacarResultPage.getFirstTrip();
//        blablacarResultPage.getLastTrip();
//    }


    @Test(dataProvider="time_and_cost_limitation_provider")
    public void firstAndLastDate(String cityFrom, String cityTo,
                                 String timeFrom, String timeTo,
                                 String lowestPrice, String highestPrice){
        BlablacarMainPage blablacarmainPage = new BlablacarMainPage();
        blablacarmainPage.open();
        blablacarmainPage.findTrip(cityFrom, cityTo);

        BlablacarResultPage blablacarResultPage = new BlablacarResultPage();
        blablacarResultPage.setTimeRange(timeFrom, timeTo);

        blablacarResultPage.setCostRange(lowestPrice, highestPrice);
    }
}
