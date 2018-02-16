package pages;

import org.openqa.selenium.By;

public class UnsplashPage extends BasePage{
    By firstImagePreview = By.xpath("(//a[@title='Download photo'])[1]");


    public UnsplashPage(){
        pageURL = "/search/photos/wallpaper";
    }


    public void downloadImage() {
        findElement(firstImagePreview);
    }
}