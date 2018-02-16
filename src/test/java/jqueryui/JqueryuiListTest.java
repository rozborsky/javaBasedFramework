package jqueryui;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.JqueryuiListPage;
import utils.BaseTest;

public class JqueryuiListTest extends BaseTest {

    @Test
    public void Search(){
        JqueryuiListPage jqueryuiListPage = new JqueryuiListPage();
        jqueryuiListPage.open();
        jqueryuiListPage.moveElement();
    }
}