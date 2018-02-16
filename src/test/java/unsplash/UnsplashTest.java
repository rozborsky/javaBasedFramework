package unsplash;

import org.testng.annotations.Test;
import pages.UnsplashPage;
import utils.BaseTest;

public class UnsplashTest extends BaseTest {

    @Test
    public void downloadingImage(){
        UnsplashPage unsplashPage = new UnsplashPage();
        unsplashPage.open();

        unsplashPage.downloadImage();
    }
}
