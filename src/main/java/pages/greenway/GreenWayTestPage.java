package pages.greenway;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.BasePage;

import java.util.Date;

public class GreenWayTestPage extends BasePage {
    private By secondsAnswer = By.xpath("//div[contains(@class,'timer_answer')]//span[@class='times']//span[@class='seconds']");
    private By secondsGeneral = By.xpath("//div[contains(@class,'total_timer')]//span[@class='times']//span[@class='seconds']");
    private By restartButton = By.xpath("//div[@class='questions']//div[contains(@class, 'time_buttons')]//div[@class='button_reset']");
    private By restartTestDialogText = By.id("ui-id-1");
    private By acceptRestartButton = By.xpath("//div[contains(@class, 'ui-dialog-buttonpane')]//button/span[contains(text(), 'Да')]");
    private By cancelRestartButton = By.xpath("//div[contains(@class, 'ui-dialog-buttonpane')]//button/span[contains(text(), 'Нет')]");
    private By currentQuestionButton = By.xpath("//div[@class='wrap_pagination']//ul[contains(@class, 'pagination')]//li[(contains(@class, 'current'))]");
    private int timeBeforeRestart;
    private String colorOfElement;
    private By answeredQuestion = By.xpath("//div[@class='wrap_pagination']//ul[contains(@class, 'pagination')]//li[(contains(@class, 'answered'))]");
    private By continueTestButton = By.xpath("//div[contains(@class, 'pause_content')]//div[(contains(@class, 'button_pause'))]");
    private By pauseButton = By.xpath("//div[contains(@class, 'time_buttons')]//div[(contains(@class, 'button_pause'))]");


    public boolean isTimersWork(String secounds) {
        waitPageLoad();
        int initialTimeQuestion = getTimeQuestionTimer();
        int initialTimeGeneral = getTimeGeneralTimer();
        long initialTime = getCutrrentTime();

        sleepThread(Integer.parseInt(secounds));
        int finalTimeQuestion = getTimeQuestionTimer();
        int finalTimeGeneral = getTimeGeneralTimer();
        long finalTime = getCutrrentTime();

        int seconds = (int)Math.floor((finalTime - initialTime) / 1000);

        return (finalTimeQuestion - initialTimeQuestion - 1 <= seconds
                &&  seconds <= finalTimeQuestion - initialTimeQuestion + 1)
                &&
                (finalTimeGeneral - initialTimeGeneral - 1 <= seconds
                &&  seconds <= finalTimeGeneral - initialTimeGeneral + 1);
    }


    private long getCutrrentTime() {
        return new Date().getTime();
    }

    private void waitPageLoad() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "eager");
    }

    private int getTimeQuestionTimer() {
        return Integer.parseInt(findElement(secondsAnswer).getText());
    }

    private int getTimeGeneralTimer() {
        return Integer.parseInt(findElement(secondsGeneral).getText());
    }

    private void sleepThread(int secounds) {
        try {
            Thread.sleep(secounds * 1000);
        } catch(InterruptedException ex) {
            //do nothing
        }
    }


    public void answerQuestion() {
        waitPageLoad();
        colorOfElement = getColorOfElement(currentQuestionButton);
        String idCurrentQuestion = findElement(currentQuestionButton).getAttribute("data-link_id");
        clickOnElement(By.xpath("//div[@class='questions']//div[@id='question_" + idCurrentQuestion + "']//ul[contains(@class, 'answers')]/li/label"));
    }


    public void restartTest() {
        clickOnElement(restartButton);
        timeBeforeRestart = getTimeGeneralTimer();
    }

    public boolean isTimerRestarted() {
        return  getTimeGeneralTimer() < timeBeforeRestart;
    }

    public void acceptRestartTest() {
        clickOnElement(acceptRestartButton);
    }


    public void cancelRestartTest() {
        clickOnElement(cancelRestartButton);
    }


    public boolean isPresentRestartDialog() {
        if (!findElement(restartTestDialogText).getText().equals("ВИ ВПЕВНЕНІ, ЩО ХОЧЕТЕ ПОЧАТИ ЩЕ РАЗ?")
                && !findElement(restartTestDialogText).getText().equals("ВЫ УВЕРЕНЫ ЧТО ХОТИТЕ НАЧАТЬ ЗАНОВО?")) {
            return false;
        }
        return true;
    }

    public String getCurrentQuestion() {
        sleepThread(1);

        return findElement(currentQuestionButton).getText();
    }


    private void clickOnElement(By element) {
        sleepThread(1);
        Long initialTime = getCutrrentTime();

        while(getCutrrentTime() - initialTime <= 10000) {
            try{
                findElement(element).click();
                return;
            } catch (Exception e) {
                sleepThread(1);
            }
        }
        throw new RuntimeException("Can't click on the element");
    }


    private String getColorOfElement(By element) {
        sleepThread(1);
        return findElement(element).getCssValue("color");
    }


    public boolean isColorOfElementChanged() {
        return !colorOfElement.equals(findElements(answeredQuestion).get(1).getCssValue("color"));
    }


    public boolean isPauseButtonPresent() {
        findElement(continueTestButton);

        return true;
    }

    public boolean isTimeTheSame() {
        return timeBeforeRestart == getTimeGeneralTimer();
    }


    public void continueTest() {
        clickOnElement(continueTestButton);
    }


    public void putOnPause() {
        clickOnElement(pauseButton);
    }


    public boolean isTimersStopped() {
        int initialTimeQuestionTimer = getTimeQuestionTimer();
        int initialTimeGeneralTimer = getTimeGeneralTimer();

        sleepThread(4);

        return initialTimeQuestionTimer == getTimeQuestionTimer()
                && initialTimeGeneralTimer == getTimeGeneralTimer();
    }
}
