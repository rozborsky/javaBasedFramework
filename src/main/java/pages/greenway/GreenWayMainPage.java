package pages.greenway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.BasePage;

import java.util.ArrayList;
import java.util.List;

public class GreenWayMainPage extends BasePage {
    By cboxCloseButton = By.id("cboxClose");
    By header = By.xpath("//div[@class='header_top']//div[@class='container']");
    By startTestButton = By.id("user_data_form_submit");
    List<String> linksTextInintial;


    public void closeCbox() {
        findElement(cboxCloseButton).click();
        try
        {
            Thread.sleep(500);
        } catch(InterruptedException ex)
        {
            //do nothing
        }
    }


    public void changeLanguage() {
        linksTextInintial = getNavigationLinks();
        String currentLanguage = getCurrentLanguage();
        findElement(header).findElement(By.className("langs")).click();
        if (currentLanguage.equals("ru")) {
            chooseLanguage("ua");
        } else {
            chooseLanguage("ru");
        }
    }

    private List<String> getNavigationLinks() {
        List<WebElement> links = findElement(header)
                    .findElements(By.xpath("//div[contains(@class, 'navigation-header-buttons')]/li/a"));

        List<String> linksText = new ArrayList<>();
        for (int i = 0; i < links.size(); i++) {
            linksText.add(links.get(i).getText());
        }

        return linksText;
    }


    private String getCurrentLanguage() {
        return findElement(header)
                .findElement(By.xpath("//div[@class='langs']//a[@class='lang']//span")).getText().toLowerCase();
    }


    private void chooseLanguage(String language) {
        findElement(header)
                .findElement(By.xpath("//div[@class='langs']//a[@class='lang']//span[contains(text(), " + language + ")]")).click();
    }


    public boolean isNavigationTextChange() {
        List<String> linksTextFinal = getNavigationLinks();
        for (int i = 0; i < linksTextFinal.size(); i++) {
            if (linksTextFinal.get(i).equals(linksTextInintial.get(i))) {
                return false;
            }
        }

        return true;
    }


    public void startTest() {
        findElement(startTestButton).click();
    }
}
