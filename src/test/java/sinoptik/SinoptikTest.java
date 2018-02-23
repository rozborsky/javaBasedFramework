package sinoptik;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.sinoptik.SinoptikMainPage;
import pages.sinoptik.SinoptikNotFoundPage;
import pages.sinoptik.SinoptikResultPage;
import utils.BaseTest;

public class SinoptikTest extends BaseTest {

    @DataProvider(name = "not_found_city_provider")
    public Object[][] notFoundCityProvider () throws Exception {
        return new String[][]{{"dfgsdfg"}};
    }


    @DataProvider(name = "number_city_in_list")
    public Object[][] numberCityInList () throws Exception {
        return new String[][]{{"Соколівка", "21"}};
    }


    @DataProvider(name = "city_identity")
    public Object[][] cityIdentity () throws Exception {
        return new String[][]{{"Соколівка", "1"}};
    }


    @DataProvider(name = "temperature_identity")
    public Object[][] temperatureIdentity () throws Exception {
        return new String[][]{{"Соколівка", "Вінницька", "Крижопільський"}};
    }

    @DataProvider(name = "shoose_city_on_map")
    public Object[][] shooseCityOnMap () throws Exception {
        return new String[][]{{"Вінниця"}};
    }


    @Test(dataProvider="not_found_city_provider")
    public void notFoundCity(String city){
        SinoptikMainPage sinoptikMainPage = new SinoptikMainPage();
        sinoptikMainPage.open();
        sinoptikMainPage.setCity(city);

        SinoptikNotFoundPage notFoundPage = new SinoptikNotFoundPage();

        Assert.assertTrue(notFoundPage.foundMessage(city));
    }


    @Test(dataProvider="number_city_in_list")
    public void numberCityInList(String city, String expectedNumber){
        SinoptikMainPage sinoptikMainPage = new SinoptikMainPage();
        sinoptikMainPage.open();
        sinoptikMainPage.setCity(city);

        Assert.assertEquals(sinoptikMainPage.getNumberOfCitiesInList(city), Integer.parseInt(expectedNumber));
    }


    @Test(dataProvider="city_identity")
    public void city_identity(String city, String numberCityInList){
        SinoptikMainPage sinoptikMainPage = new SinoptikMainPage();
        sinoptikMainPage.open();
        sinoptikMainPage.chooseCityFromList(city, Integer.parseInt(numberCityInList));

        Assert.assertTrue(sinoptikMainPage.isCitiesIdentical());
    }


    @Test(dataProvider="temperature_identity")
    public void temperature_identity(String city, String region, String district){
        SinoptikMainPage sinoptikMainPage = new SinoptikMainPage();
        sinoptikMainPage.open();
        sinoptikMainPage.chooseCityInRegionAndDistrict(city, region, district);

        SinoptikResultPage resultPage = new SinoptikResultPage();
        Assert.assertTrue(resultPage.isTemperatureTheSame());
    }


//    @Test(dataProvider="shoose_city_on_map")
//    public void shooseCityOnMap(String city){
//        SinoptikMainPage sinoptikMainPage = new SinoptikMainPage();
//        sinoptikMainPage.open();
//        sinoptikMainPage.showMap();
//        System.out.println();
//    }


    @Test
    public void tooltip(){
        SinoptikResultPage sinoptikResultPage = new SinoptikResultPage();
        sinoptikResultPage.open();
        sinoptikResultPage.isShowedWindDirection();
    }
}
