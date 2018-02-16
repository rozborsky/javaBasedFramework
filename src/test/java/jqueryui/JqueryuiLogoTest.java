package jqueryui;

import org.testng.annotations.Test;
import pages.JqueryuiLogoPage;
import utils.BaseTest;

public class JqueryuiLogoTest extends BaseTest {
    @Test
    public void Search(){
        JqueryuiLogoPage jqueryuiPage = new JqueryuiLogoPage();
        jqueryuiPage.open();
        jqueryuiPage.hideElement();
    }
}