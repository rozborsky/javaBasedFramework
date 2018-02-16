package encodable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.EncodableUploadingPage;
import pages.EncodableViewUploadedFilesPage;
import utils.BaseTest;

/**
 * Created by Kos on 7/17/17.
 */
public class EncodableTest extends BaseTest {

    @DataProvider(name = "searches_request_provider")
    public Object[][] provider () throws Exception {
        return new String[][]{{"test", "file.jpg"}};
    }


    @Test(dataProvider="searches_request_provider")
    public void Search(String subfolderName, String fileName){
        EncodableUploadingPage encodableUploadingPage = new EncodableUploadingPage();
        encodableUploadingPage.open();
        encodableUploadingPage.uploadFile(subfolderName, fileName);

        EncodableViewUploadedFilesPage encodableViewUploadedFilesPage = new EncodableViewUploadedFilesPage();
        encodableViewUploadedFilesPage.isFileUploaded(subfolderName, fileName);
    }
}
