package greenway;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.greenway.GreenWayMainPage;
import pages.greenway.GreenWayTestPage;
import utils.BaseTest;

/**
 * Created by Kos on 7/17/17.
 */
public class GreenwayTest extends BaseTest {
    @DataProvider(name = "timer_provider")
    public Object[][] timerProvider () throws Exception {
        return new String[][]{{"1"}, {"2"}, {"3"}, {"4"}, {"5"}, {"6"}, {"7"},
                {"8"}, {"10"}, {"9"}, {"11"}, {"12"}, {"13"}, {"14"}, {"17"}, {"18"}};
    }
    @Test
    public void changeLanguage(){
        GreenWayMainPage greenWayMainPage = new GreenWayMainPage();
        greenWayMainPage.open();
        greenWayMainPage.closeCbox();
        greenWayMainPage.changeLanguage();
        Assert.assertTrue(greenWayMainPage.isNavigationTextChange());
    }

    @Test(dataProvider="timer_provider")
    public void checkTimer(String seconds){
        GreenWayMainPage greenWayMainPage = new GreenWayMainPage();
        greenWayMainPage.open();
        greenWayMainPage.closeCbox();
        greenWayMainPage.startTest();


        GreenWayTestPage greenWayTestPage = new GreenWayTestPage();
        Assert.assertTrue(greenWayTestPage.isTimersWork(seconds));
    }

    @Test
    public void acceptRestartTest(){
        GreenWayMainPage greenWayMainPage = new GreenWayMainPage();
        greenWayMainPage.open();
        greenWayMainPage.closeCbox();
        greenWayMainPage.startTest();

        GreenWayTestPage greenWayTestPage = new GreenWayTestPage();
        greenWayTestPage.answerQuestion();
        greenWayTestPage.restartTest();
        Assert.assertTrue(greenWayTestPage.isPresentRestartDialog());
        greenWayTestPage.acceptRestartTest();
        Assert.assertEquals(greenWayTestPage.getCurrentQuestion(), "1");
        Assert.assertTrue(greenWayTestPage.isTimerRestarted());
    }


    @Test
    public void buttonRestartAndCancelTest(){
        GreenWayMainPage greenWayMainPage = new GreenWayMainPage();
        greenWayMainPage.open();
        greenWayMainPage.closeCbox();
        greenWayMainPage.startTest();

        GreenWayTestPage greenWayTestPage = new GreenWayTestPage();
        greenWayTestPage.answerQuestion();
        greenWayTestPage.restartTest();
        greenWayTestPage.cancelRestartTest();

        Assert.assertTrue(greenWayTestPage.isPauseButtonPresent());
        greenWayTestPage.continueTest();
        Assert.assertTrue(greenWayTestPage.isTimeTheSame());
        Assert.assertEquals(greenWayTestPage.getCurrentQuestion(), "2");
    }


    @Test
    public void changeColorQuestionTest(){
        GreenWayMainPage greenWayMainPage = new GreenWayMainPage();
        greenWayMainPage.open();
        greenWayMainPage.closeCbox();
        greenWayMainPage.startTest();

        GreenWayTestPage greenWayTestPage = new GreenWayTestPage();
        greenWayTestPage.answerQuestion();

        Assert.assertEquals(greenWayTestPage.getCurrentQuestion(), "2");
        Assert.assertTrue(greenWayTestPage.isColorOfElementChanged());
    }


    @Test
    public void threeQuestionAndContinueTest(){
        GreenWayMainPage greenWayMainPage = new GreenWayMainPage();
        greenWayMainPage.open();
        greenWayMainPage.closeCbox();
        greenWayMainPage.startTest();

        GreenWayTestPage greenWayTestPage = new GreenWayTestPage();
        greenWayTestPage.answerQuestion();
        greenWayTestPage.answerQuestion();
        greenWayTestPage.answerQuestion();

        greenWayTestPage.putOnPause();
        Assert.assertTrue(greenWayTestPage.isTimersStopped());

        greenWayTestPage.continueTest();
        Assert.assertTrue(greenWayTestPage.isTimersWork("3"));
    }
}
