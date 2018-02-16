package pages;


import org.openqa.selenium.By;

/**
 * Created by Admin on 10/20/2017.
 */
public class EncodableViewUploadedFilesPage extends BasePage{

    public void isFileUploaded(String subfolderName, String fileName) {
        findElement(By.linkText(subfolderName)).click();
        findElement(By.linkText(fileName));
    }
}
