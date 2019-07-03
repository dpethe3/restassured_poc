import Common.AutomationFrameworkBase;
import Utilities.ExtentReports.ExtentTestManager;
import Utilities.TestDataProvider;
import com.relevantcodes.extentreports.ExtentTest;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Properties;

import static com.relevantcodes.extentreports.LogStatus.FAIL;
import static com.relevantcodes.extentreports.LogStatus.PASS;

public class APITests
{




    public static final int	HTTP_STATUS_OK	= 200;
    public static  String LLP_URL="https://momentfeed-prod.apigee.net/api/llp.json?auth_token=";

    public JsonPath jp;
    public static String env="";
/*
    public APITests(){
        AutomationFrameworkBase base=new AutomationFrameworkBase();
        base.loadConfigProperties("config.properties");
        if(AutomationFrameworkBase.CONFIG.getProperty("env")=="qa"){
            LLP_URL=AutomationFrameworkBase.CONFIG.getProperty("url_qa");

        }
        else{
            LLP_URL=AutomationFrameworkBase.CONFIG.getProperty("prod_url");
        }
    }
*/
@Test(dataProvider ="authHeader_queryfile",dataProviderClass = TestDataProvider.class)
public void validateAPItest(String client_id,String authToken,String store_name,String brand_name,String corporate_id,String store_address,String phoneNumber,
                            String store_count){

    RestAssured.baseURI=LLP_URL+authToken;
    Response res = RestAssured.given().log().everything().get(RestAssured.baseURI);
    String response=res.getBody().asString();
    ResponseBody jsonbody=res.getBody();
    int status_code=res.getStatusCode();
    String line=res.getStatusLine();
    try {

        Assert.assertEquals(200, status_code);
//        ExtentTestManager.getTest().log(PASS, "Verify Response status code", "Response code is " + status_code + "Expected Response code was 200");
        //ExtentTest et = ExtentTestManager.getTest();
       // et.log(PASS, "Verify Response status code", "Response code is " + status_code + "Expected Response code was 200");;
        String body=response.substring(1,response.length()-1);
        JSONObject obj = new JSONObject(body);
       String address=obj.getJSONObject("store_info").getString("address");
       String brandname= obj.getJSONObject("store_info").getString("brand_name");
        String storename= obj.getJSONObject("store_info").getString("name");
        String corporateid=obj.getJSONObject("store_info").getString("corporate_id");
        String phonenum=obj.getJSONObject("store_info").getString("phone");
        int no_of_Stores=obj.getJSONObject("store_info").length();
        Assert.assertEquals(brandname, brand_name);
      //  et.log(PASS, "Verify brand name", "Brand name is " + brandname + "Expected brand name  was" +brand_name);

        Assert.assertEquals(storename, store_name);
        //et.log(PASS, "Verify store name ", "Store name is " + storename + "Expected store name is"+ store_name);
         Assert.assertEquals(corporateid, corporate_id);
        Assert.assertEquals(address, store_address);
        Assert.assertEquals(phonenum, phoneNumber);
        Assert.assertEquals(no_of_Stores, store_count);
       // System.out.println(obj.getJSONObject("store_info"));
      // ExtentTestManager.getTest().setDescription("Verify Response status code");
       // et.log(PASS, "Verify corporate id ", "Corporate id is " + corporateid + "Expected corporateid is"+ corporate_id);
    }
    catch (AssertionError e){
        System.out.println(e.getMessage());
        ExtentTestManager.startTest("Verify Response Code","response code verify");
        ExtentTestManager.getTest().log(FAIL,"Verify Response code","Response code is "+status_code+" Expected Response code was 200");

    }
    try{
        Assert.assertTrue(line.equals("HTTP/1.1 200 OK"),"Status Line is incorrect");

        //ExtentTestManager.getTest().setDescription("Verify Response Status Line");
       // ExtentTestManager.getTest().log(PASS,"Verify Response status Line","Response Status line is "+line);
    }

    catch(AssertionError e1){
        ExtentTestManager.startTest("Verify Response Status Line","Resonse Status Line");
        ExtentTestManager.getTest().log(FAIL,"Verify Response status Line","Response Status line is "+line+"Expected Response status line is HTTP/1.1 200 OK");
    }


}




}
