import RestLibrary.RestLibr;
import Utilities.ExtentReports.ExtentTestManager;
import com.sun.xml.bind.v2.runtime.output.FastInfosetStreamWriterOutput;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.relevantcodes.extentreports.LogStatus.FAIL;
import static com.relevantcodes.extentreports.LogStatus.PASS;

public class APIGetTest {

    public Response RecievedRespose;
    public String responseBody;
    public JsonPath jp;
    RestLibr rb = new RestLibr();

    @BeforeClass
    public void getRssponseBody() {
        RecievedRespose = rb.GetAPICall("https://momentfeed-prod.apigee.net/api/llp.json?auth_token=IVNLPNUOBXFPALWE");
        responseBody = RecievedRespose.getBody().asString();
         jp = RecievedRespose.jsonPath();
    }


    @Test
    public void verifyReesponseCode() {
        int code = RecievedRespose.getStatusCode();

        try {

           Assert.assertEquals(code, 20, "Response Code");

          ExtentTestManager.getTest().setDescription("Vertify Response code");
            ExtentTestManager.getTest().log(PASS,"Verify Response code","Rssponse code is "+code+"Expected Response code was 200");

        }
        catch (AssertionError e){
            ExtentTestManager.startTest("Verify Response Code","response code verify");
            ExtentTestManager.getTest().log(FAIL,"Verify Response code","Rssponse code is "+code+"Expected Response code was 200");



        }


    }



    @Test

    public void verifyResponseStatusLine()
    {
        String line=RecievedRespose.getStatusLine();
       Assert.assertEquals(line,"HTTP/1.1 200 OK","Status Line");
        ExtentTestManager.startTest("Verify Response Status Line","Resonse Status Line");
        ExtentTestManager.getTest().log(PASS,"Verify Response status Line","Rssponse Status line is "+line);

    }

    @Test

    public void verifyNumberOfStores()
    {
        jp.getList("$[*].store_info");
        System.out.println(
                jp.getList("$[*].store_info"));
    }
}

