package pages;


import org.openqa.selenium.By;

/**
 * Created by Admin on 10/20/2017.
 */
public class EncodableUploadingPage extends BasePage{
    private By subfolder = By.id("newsubdir1");
    private By emailField = By.id("formfield-email_address");
    private By firstNameField = By.id("formfield-first_name");
    private By fileChooseButton = By.id("uploadname1");
    private By fileUploadButton = By.id("uploadbutton");
    private By viewUploadFileButton = By.linkText("View Uploaded Files");//*[text()='the visible text']
    private By sucsessFileUpload = By.xpath("//*[text()='Your upload is complete:']");
    private String email = "roman.rozborsky@gmail.com";
    private String filePath = "C:/Users/comp/Desktop/";


    private void fillForm(String subfolderName) {
        findElement(subfolder).sendKeys(subfolderName);
        findElement(emailField).sendKeys(email);
        findElement(firstNameField).sendKeys("roman");
    }


    public void uploadFile(String subfolderName, String fileName) {
        fillForm(subfolderName);
        findElement(fileChooseButton).sendKeys(filePath + fileName);
        findElement(fileUploadButton).click();
        findElement(fileUploadButton).click();

        findElement(sucsessFileUpload);
        findElement(viewUploadFileButton).click();
    }
}
