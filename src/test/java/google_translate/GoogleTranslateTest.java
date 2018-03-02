package google_translate;

import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.googleTranslate.AuthorisationPage;
import pages.googleTranslate.GoogleTranslatePage;
import utils.BaseTest;

/**
 * Created by Kos on 7/17/17.
 */
public class GoogleTranslateTest extends BaseTest {

    @DataProvider(name = "word_provider")
    public Object[][] wordProvider () throws Exception {
        return new String[][]{{"Kaka", "Writing"}};
    }


    @DataProvider(name = "phrasebook_provider")
    public Object[][] phrasebookProvider () throws Exception {
        return new String[][]{{"Kaka", "romanrozborskytestpage", "testpage"}};
    }


    @DataProvider(name = "russian_english_provider")
    public Object[][] russianToEnglishProvider () throws Exception {
        return new String[][]{{"какашка", "shit", "7"}};
    }


    @Test(dataProvider="word_provider")
    public void translateFromJapaneese(String originalWord, String expectedTranslation){
        GoogleTranslatePage googleTranslatePage = new GoogleTranslatePage();
        googleTranslatePage.open();
        googleTranslatePage.setEnglishLanguage();
        googleTranslatePage.setWord(originalWord);
        googleTranslatePage.chooseJapanese();

        Assert.assertTrue(googleTranslatePage.isJapaneeseAndIsExpectedWord(expectedTranslation));
    }


    @Test(dataProvider="phrasebook_provider")
    public void addWordToPrasebook(String originalWord, String email, String password){
        GoogleTranslatePage googleTranslatePage = new GoogleTranslatePage();
        googleTranslatePage.open();
        googleTranslatePage.logInToTheSystem();

        AuthorisationPage authorisationPage = new AuthorisationPage();
        authorisationPage.entryIntoTheSystem(email, password);

        googleTranslatePage.setEnglishLanguage();
        googleTranslatePage.setWord(originalWord);
        googleTranslatePage.chooseJapanese();
        googleTranslatePage.saveToPhrasebook();
        googleTranslatePage.openPhrasebook();
        googleTranslatePage.isWordInPhrasebook();
        googleTranslatePage.deleteWord();

        Assert.assertFalse(googleTranslatePage.isWordNotInPrasebook());
    }


    @Test(dataProvider="russian_english_provider")
    public void translateToJapaneese(String originalWord, String expectedTranslation, String wordLength){
        GoogleTranslatePage googleTranslatePage = new GoogleTranslatePage();
        googleTranslatePage.open();
        googleTranslatePage.setRussianAndEnglish();
        googleTranslatePage.setWord(originalWord);
        googleTranslatePage.findTranscription();

        Assert.assertTrue(googleTranslatePage.isLengthOfWordWright(wordLength));
        Assert.assertTrue(googleTranslatePage.isWordWright(expectedTranslation));
    }
}
