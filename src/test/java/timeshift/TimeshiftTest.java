package timeshift;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.TimeShift;
import utils.BaseTest;

public class TimeshiftTest  extends BaseTest {
    @DataProvider(name = "timeshift_provider")
    public Object[][] provider () throws Exception {
        return new String[][]{{"rrozborskyi", "timTim11", "16"}};
    }

    @Test(dataProvider="timeshift_provider")
    public void Search(String login, String password, String hours){

        TimeShift timeShift = new TimeShift();
        timeShift.open();
        timeShift.signIn(login, password);

        System.out.println(timeShift.getTotalTime());
        Assert.assertEquals(timeShift.getTotalTime(), hours);
    }
}
