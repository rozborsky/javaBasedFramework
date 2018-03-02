package pages.googleTranslate;

import org.openqa.selenium.By;
import pages.BasePage;

public class GoogleTranslatePage extends BasePage {
    private By logInButton = By.xpath("//div[@id='gb']//a[contains(text(), 'Войти')]");
    private By chooseEnglishButtonOriginal = By.xpath("//div[@id='gt-sl-sugg']//div[@value='en']");
    private By chooseRussianButtonOriginal = By.xpath("//div[@id='gt-sl-sugg']//div[@value='ru']");
    private By chooseEnglishButtonTranslate = By.xpath("//div[@id='gt-tl-sugg']//div[@value='en']");
    private By chooseLanguagesButtonOriginal = By.xpath("//div[@id='gt-sl-gms']");
    private By chooseLanguagesButtonTranslate = By.xpath("//div[@id='gt-tl-gms']");
    private By japaneseButton = By.xpath("//div[@id='gt-sl-sugg']//div[contains(@class, 'jfk-button-checked')]");

    private String idEnglishOriginal = ":9";
    private String idEnglishTranslate = ":39";
    private String idRussianOriginal = ":21";

    private By resultWord = By.xpath("//span[@id='result_box']//span");
    private By addToPhrasebooklink = By.xpath("//div[@id='gt-pb-star']/div");
    private By textareaInput = By.id("source");
    private By wordLength = By.id("gt-src-cc-ctr");
    private By transcription = By.id("src-translit");

    private By japaneseLink = By.xpath("//div[@id='spelling-correction']//a");
    private By showPhrasebookButton = By.xpath("//div[@id='gt-pb-sw1']/div");
    private By wordInPhrasebook;
    private By deleteButton = By.xpath("//div[@id='pb-del-c']/div");


    public void setEnglishLanguage() {
        try {
            findElement(chooseEnglishButtonOriginal).click();
        } catch (RuntimeException e) {
            chooseLanguageFromList(chooseLanguagesButtonOriginal, idEnglishOriginal);
        }
        try {
            findElement(chooseEnglishButtonTranslate).click();
        } catch (RuntimeException e) {
            chooseLanguageFromList(chooseLanguagesButtonTranslate, idEnglishTranslate);
        }
    }


    private void chooseLanguageFromList(By chooseLanguagesButton, String id) {
        findElement(chooseLanguagesButton).click();
        findElement(By.xpath("//div[@id='" + id + "']//div")).click();
    }


    public void setWord(String word) {
        findElement(textareaInput).sendKeys(word);
        wordInPhrasebook = By.xpath("//table[@id='gt-pb-tb']//div[contains(text(), '" + word + "')]");
    }


    public void chooseJapanese() {
        findElement(japaneseLink).click();
    }


    public boolean isJapaneeseAndIsExpectedWord(String expectedTranslation) {
        return isJapaneeseChecked() && isWordWright(expectedTranslation);
    }

    private boolean isJapaneeseChecked() {
        return findElement(japaneseButton).getText().equals("японский");
    }


    public boolean isWordWright(String expectedTranslation) {
        return findElement(resultWord).getText().equals(expectedTranslation);
    }


    public void saveToPhrasebook() {
        findElement(addToPhrasebooklink).click();
    }


    public void logInToTheSystem() {
        findElement(logInButton).click();
    }


    public void isWordInPhrasebook() throws RuntimeException {
        findElement(wordInPhrasebook);
    }


    public void openPhrasebook() {
        findElement(showPhrasebookButton).click();
    }


    public void deleteWord() {
        findElement(wordInPhrasebook).findElement(By.xpath(".//ancestor::tr//span[@role='checkbox']")).click();
        findElement(deleteButton).click();
    }


    public boolean isWordNotInPrasebook() {
        try {
            isWordInPhrasebook();
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }


    public void setRussianAndEnglish() {
        try {
            findElement(chooseRussianButtonOriginal).click();
        } catch (RuntimeException e) {
            chooseLanguageFromList(chooseLanguagesButtonOriginal, idRussianOriginal);
        }
        try {
            findElement(chooseEnglishButtonTranslate).click();
        } catch (RuntimeException e) {
            chooseLanguageFromList(chooseLanguagesButtonTranslate, idEnglishTranslate);
        }
    }


    public boolean isLengthOfWordWright(String expectedLength) {
        String string = findElement(wordLength).getText();
        return
                Integer.parseInt(string.substring(0, string.indexOf("/")))
                        == Integer.parseInt(expectedLength);
    }


    public void findTranscription() {
        findElement(transcription);
    }
}
