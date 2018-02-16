package jqueryui;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.JqueryuiDatepickerPage;
import pages.JqueryuiListPage;
import utils.BaseTest;

public class JqueryuiDatepickerTest extends BaseTest {

    @DataProvider(name = "searches_request_provider")
    public Object[][] provider () throws Exception {
        return new String[][]{{"18"}};
    }


    @Test(dataProvider="searches_request_provider")
    public void Search(String date){
        JqueryuiDatepickerPage jqueryuiDatepickerPage = new JqueryuiDatepickerPage();
        jqueryuiDatepickerPage.open();
        jqueryuiDatepickerPage.choseDate(date);
    }
}