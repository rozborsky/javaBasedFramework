package pages.sinoptik;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.BasePage;

public class SinoptikResultPage extends BasePage {
    private By saturdayLabel = By.xpath("//div[@id='blockDays']//div[@class='tabs']//a[contains(text(), 'Субота')]");
    private By saturdayTitle = By.xpath("//div[@id='bd2c']//div[@class='calendBlock']//p[contains(text(), 'Субота')]");
    private By saturdayMorningMinTemperature7 = By.xpath("//div[@id='bd2c']//tr[@class='temperature']//td[@class='p3  ']");
    private By saturdayMorningMaxTemperature7 = By.xpath("//div[@id='bd2c']//tr[@class='temperature']//td[@class='p4 bR ']");
    private By saturdayMorningMinTemperature10 = By.xpath("//div[@id='blockDays']//tr[@class='temperature']//td[@class='p3  ']");
    private By saturdayMorningMaxTemperature10 = By.xpath("//div[@id='blockDays']//tr[@class='temperature']//td[@class='p4 bR ']");
    private By tenDayModeLink = By.xpath("//div[@id='topMenu']//a[contains(text(), ' 10 днів ')]");
    private By fridayTitle= By.xpath("//div[@id='blockDays']//div[@class='main loaded']//p[contains(text(), 'ятниця')]");
    private int minMorningTemperatureSaturday7;
    private int maxMorningTemperatureSaturday7;
    private int minMorningTemperatureSaturday10;
    private int maxMorningTemperatureSaturday10;


    public boolean isTemperatureTheSame() {
        selectSaturday();

        getMorningTemperatureSaturday7();
        selectTenDayMode();
        getShowedDay();

        selectSaturday();
        getMorningTemperatureSaturday10();

        return minMorningTemperatureSaturday7 == minMorningTemperatureSaturday10
                && maxMorningTemperatureSaturday7 == maxMorningTemperatureSaturday10;
    }

    private void selectSaturday() {
        findElement(saturdayLabel).click();
        findElement(saturdayTitle).getText();
    }


    private void getMorningTemperatureSaturday7() {
        String minTemperature = findElement(saturdayMorningMinTemperature7).getText();
        minMorningTemperatureSaturday7 = Integer.parseInt(minTemperature.substring(0, minTemperature.length() - 1));

        String maxTemperature = findElement(saturdayMorningMaxTemperature7).getText();
        maxMorningTemperatureSaturday7 = Integer.parseInt(maxTemperature.substring(0, minTemperature.length() - 1));
    }


    private void getMorningTemperatureSaturday10() {
        String minTemperature = findElement(saturdayMorningMinTemperature10).getText();
        minMorningTemperatureSaturday10 = Integer.parseInt(minTemperature.substring(0, minTemperature.length() - 1));

        String maxTemperature = findElements(saturdayMorningMaxTemperature10).get(1).getText();
        maxMorningTemperatureSaturday10 = Integer.parseInt(maxTemperature.substring(0, minTemperature.length() - 1));
    }


    private void selectTenDayMode() {
        findElement(tenDayModeLink).click();
    }


    private void getShowedDay() {
        findElement(fridayTitle);
    }


    public void isShowedWindDirection() {
        clickOnWindDirection();
        findElement(By.xpath("//div[@id='tooltipS']//div[@class='tooltip-tip-content']"));
    }


    private void clickOnWindDirection() {
        WebElement windDirection = findElements(By.xpath("//div[@id='blockDays']//table[@class='weatherDetails']//tr[@class='gray']")).get(1);
        windDirection.findElement(By.tagName("div")).click();
    }
}