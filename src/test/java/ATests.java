import Common.AutomationFrameworkBase;
import Common.Constants;
import RestLibrary.RestMethods;
import Utilities.ExtentReports.ExtentManager;
import Utilities.ExtentReports.ExtentTestManager;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;
import java.lang.reflect.Method;
import static org.hamcrest.Matchers.*;
import java.io.IOException;


import static Common.Constants.RESPONSE_STATUS_CODE_200;
import static io.restassured.RestAssured.given;

public class ATests extends AutomationFrameworkBase {

    AutomationFrameworkBase testBase;
    String serviceUrl;
    String apiUrl;
    String url;
    RestMethods restClient;
    CloseableHttpResponse closebaleHttpResponse;

    @BeforeMethod

    public void setUp() throws ClientProtocolException, IOException {
        testBase = new AutomationFrameworkBase();
        serviceUrl = prop.getProperty("URL");
        apiUrl = prop.getProperty("serviceURL");
        url = serviceUrl + apiUrl;

    }


    @Test ()

    public void checkstatucode()

    {


        given().
                when().
                get("https://momentfeed-prod.apigee.net/api/llp.json?auth_token=IVNLPNUOBXFPALWE").
                then().
                assertThat().
                body("$[*][store_info]",hasSize(20));


        ExtentTestManager.getTest().setDescription("Verify the Number of stores ");



    }

    @Test(priority = 1,description = "Invalid Login Scenario with wrong username and password.")
    public void getAPITestWithoutHeaders() throws ClientProtocolException, IOException {
        restClient = new RestMethods();
        closebaleHttpResponse = restClient.get(url);

        //a. Status Code:
        int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
        ExtentTestManager.startTest("Ststus code", "Invalid Login Scenario with empty username and password.");
        ExtentTestManager.getTest().setDescription("Verify the Sattus code of resposne");

        System.out.println("Status Code--->" + closebaleHttpResponse);
        System.out.println("Status Code--->" + statusCode);

        Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");

        //b. Json String:
        String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");

        JSONObject responseJson = new JSONObject(responseString.substring(responseString.indexOf('{')));
        System.out.println("Response JSON from API---> "+ responseJson);



    }


}
