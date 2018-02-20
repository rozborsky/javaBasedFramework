package google.stories;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import pages.jbehave.GooglePage;
import pages.jbehave.GoogleResultPage;

import static org.junit.Assert.assertThat;


public class StepsDefinition {
    private GooglePage googlePage;

    @Given("open Google")
    public void openGoogle() {
        googlePage = new GooglePage();
        googlePage.openGoogle();
    }

    @When("searching for '$value'")
    public  void search(String value) {
        googlePage.performSearch(value);
    }


    @Then("finding a lot of '$value' in titles")
    public void checkTitles(String value) {
        GoogleResultPage googleResultPage = new GoogleResultPage(value);
        System.out.println("isWordPresentInAllTitles - " + googleResultPage.isWordPresentInAllTitles());

        Assert.assertEquals(googleResultPage.getNumberREsultsInTitles(), googleResultPage.getNumberREsults());
    }


//    @Then("finding a lot of '$value' in links")
//    public void checkLinks(String value) {
//        GoogleResultPage googleResultPage = new GoogleResultPage(value);
//        System.out.println("isWordPresentInAllLinks - " + googleResultPage.isWordPresentInAllLinks());
//
//        Assert.assertEquals(googleResultPage.getNumberREsultsIlLinks(), googleResultPage.getNumberREsults());
//    }
//
//
//
//    @Then("finding a lot of '$value' in texts")
//    public void checkInTexts(String value) {
//        GoogleResultPage googleResultPage = new GoogleResultPage(value);
//
//        System.out.println("isWordPresentInAllTexts - " + googleResultPage.isWordPresentInAllTexts());
//        googlePage.closeGoogle();
//        Assert.assertEquals(googleResultPage.getNumberREsultsInTexts(), googleResultPage.getNumberREsults());
//    }
}
