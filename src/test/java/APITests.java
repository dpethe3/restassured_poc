import Utilities.ExtentReports.ExtentTestManager;
import Utilities.TestDataProvider;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.relevantcodes.extentreports.LogStatus.FAIL;
import static com.relevantcodes.extentreports.LogStatus.PASS;

public class APITests
{
    public static final int	HTTP_STATUS_OK	= 200;
    public static final String LLP_URL="https://momentfeed-prod.apigee.net/api/llp.json?auth_token=";


@Test(dataProvider ="authHeader_queryfile",dataProviderClass = TestDataProvider.class)
public void validateAPItest(String client_id,String authToken){

    RestAssured.baseURI=APITests.LLP_URL+authToken;
    Response res = RestAssured.given().log().everything().get(RestAssured.baseURI);
    String response=res.getBody().asString();
    int status_code=res.getStatusCode();
    String line=res.getStatusLine();
    try {
        Assert.assertEquals(200, status_code);
      //  ExtentTestManager.getTest().setDescription("Verify Response status code");
       // ExtentTestManager.getTest().log(PASS, "Verify Response status code", "Response code is " + status_code + "Expected Response code was 200");
    }
    catch (AssertionError e){
        ExtentTestManager.startTest("Verify Response Code","response code verify");
        ExtentTestManager.getTest().log(FAIL,"Verify Response code","Response code is "+status_code+"Expected Response code was 200");



    }
    try{
        Assert.assertTrue(line.equals("HTTP/1.1 200 OK"),"Status Line is incorrect");
       // ExtentTestManager.getTest().setDescription("Verify Response Status Line");
       // ExtentTestManager.getTest().log(PASS,"Verify Response status Line","Response Status line is "+line);
    }

    catch(AssertionError e1){
        ExtentTestManager.startTest("Verify Response Status Line","Resonse Status Line");
        ExtentTestManager.getTest().log(FAIL,"Verify Response status Line","Response Status line is "+line+"Expected Response status line is HTTP/1.1 200 OK");
    }


}




}
