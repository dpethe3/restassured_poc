import Common.AutomationFrameworkBase;
import Common.Constants;
import Common.functionLibrary;
import RestLibrary.RestUtil;
import Utilities.ExtentR.ExtentManager;
import Utilities.TestDataProvider;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class DAPITests {
    RestUtil restutil = null;
    AutomationFrameworkBase workbase = null;
    ExtentManager manager = new ExtentManager();
    functionLibrary library = new functionLibrary();


    @BeforeClass(alwaysRun = true)
    public void setup() {
        restutil = new RestUtil();
        workbase = new AutomationFrameworkBase();


    }



    @Test(dataProvider = "authHeader_queryfile", dataProviderClass = TestDataProvider.class)
    public void validateAPItest(String client_id, String authToken, String store_name, String brand_name, String corporate_id, String store_address, String phoneNumber,
                                String store_count) {
       String ReportName= store_name+"_"+library.getTimeStamp();
        ExtentReports extent = manager.GetReport(ReportName);

        RestAssured.baseURI = restutil.checkenv() + authToken;
        Response res = RestAssured.given().log().everything().get(RestAssured.baseURI);
        String response = res.getBody().asString();
        ResponseBody jsonbody = res.getBody();
        int status_code = res.getStatusCode();
        String line = res.getStatusLine();
        String body=response.substring(1,response.length()-1);
        JSONObject obj = new JSONObject(body);
        String address=obj.getJSONObject("store_info").getString("address");
        String brandname= obj.getJSONObject("store_info").getString("brand_name");
        String storename= obj.getJSONObject("store_info").getString("name");
        String corporateid=obj.getJSONObject("store_info").getString("corporate_id");
        String phonenum=obj.getJSONObject("store_info").getString("phone");
        int no_of_Stores=obj.getJSONObject("store_info").length();
        


        // call createTest method and pass the name of TestCase- Based on your requirement
        ExtentTest test1 = extent.createTest("Verify Response code");
        test1.log(Status.INFO, "Response code Recieved was " + status_code);

        //@Test -1 Verify Status Code
        if (library.verifyNumberValue(Constants.RESPONSE_STATUS_CODE_200, status_code)) {
            test1.log(Status.PASS, "Status Code Verified Successfully");
        } else {
            test1.log(Status.FAIL, "Status Code could not be verified. Expected Status code was :" + Constants.RESPONSE_STATUS_CODE_200);
        }


        //@Test2  Verify Status Line
        ExtentTest test2 = extent.createTest("Verify Status Line");
        test2.log(Status.INFO, "Status Line Recieved in Response is :" + line);
        if (library.verifyStringValue(Constants.RESPONSE_STATUS_lINE, line)) {
            test2.log(Status.PASS, "Status Line Verified Successfully");
        } else {
            test2.log(Status.FAIL, "Status Line Could not Verified. Expected Status Line was :" + Constants.RESPONSE_STATUS_lINE);
        }


        //@Test3  Verify store address
        ExtentTest test3 = extent.createTest("Verify Store address");
        test3.log(Status.INFO, "Store address Recieved in Response is :" + address);
        if (library.verifyStringValue(store_address, address)) {
            test3.log(Status.PASS, "Store address Verified Successfully");
        } else {
            test3.log(Status.FAIL, "Store address Could not be Verified. Expected address was :" + store_address);
        }

        //@Test4  Verify brand name
        ExtentTest test4 = extent.createTest("Verify brand name");
        test4.log(Status.INFO, "brand name Recieved in Response is :" + brandname);
        if (library.verifyStringValue(brand_name, brandname)) {
            test4.log(Status.PASS, "brand name Verified Successfully");
        } else {
            test4.log(Status.FAIL, "brand name Could not be Verified. Expected brand name was :" + brand_name);
        }

        //@Test5  Verify store name
        ExtentTest test5 = extent.createTest("Verify store name");
        test5.log(Status.INFO, "Store name Recieved in Response is :" + storename);
        if (library.verifyStringValue(store_name, storename)) {
            test5.log(Status.PASS, "Store name Verified Successfully");
        } else {
            test5.log(Status.FAIL, "Store name Could not be Verified. Expected Store name was :" + store_name);
        }

        //@Test6  Verify Corporateid
        ExtentTest test6 = extent.createTest("Verify Corporate ID");
        test6.log(Status.INFO, "Corporate ID Recieved in Response is :" + corporateid);
        if (library.verifyStringValue(corporate_id, corporateid)) {
            test6.log(Status.PASS, "Corporate ID Verified Successfully");
        } else {
            test6.log(Status.FAIL, "Corporate ID Could not be Verified. Expected Corporate ID was :" + corporate_id);
        }

        //@Test7  Verify Phonenum
        ExtentTest test7 = extent.createTest("Verify Store Phone Number");
        test7.log(Status.INFO, "Phonenum Recieved in Response is :" + phonenum);
        if (library.verifyStringValue(phoneNumber, phonenum)) {
            test7.log(Status.PASS, "Store Phone Number Verified Successfully");
        } else {
            test7.log(Status.FAIL, "Store Phone Number Could not be Verified. Expected Store Phone Number was :" + phoneNumber);
        }

        //@Test8  Verify Store count
        ExtentTest test8 = extent.createTest("Verify Store Count");

        test8.log(Status.INFO, "Store count for given client Recieved in Response is :" + no_of_Stores);
        if (library.verifyNumberValue(Integer.parseInt(store_count), no_of_Stores)) {
            test8.log(Status.PASS, "Store count for client Verified Successfully");
        } else {
            test8.log(Status.FAIL, "Store count for client Could not be Verified. Expected Store count was :" + store_count);
        }
        extent.flush();

        /*try {

            Assert.assertEquals(200, status_code);
            test1.log(Status.PASS, "status verified");
            extent.flush();
            String body = response.substring(1, response.length() - 1);
            JSONObject obj = new JSONObject(body);
            String address = obj.getJSONObject("store_info").getString("address");
            String brandname = obj.getJSONObject("store_info").getString("brand_name");
            String storename = obj.getJSONObject("store_info").getString("name");
            String corporateid = obj.getJSONObject("store_info").getString("corporate_id");
            String phonenum = obj.getJSONObject("store_info").getString("phone");
            int no_of_Stores = obj.getJSONObject("store_info").length();
            Assert.assertEquals(brandname, brand_name);
            logger.log(Status.PASS, "brand name verified verified");
            Assert.assertEquals(storename, store_name);
            Assert.assertEquals(corporateid, corporate_id);
            Assert.assertEquals(address, store_address);
            Assert.assertEquals(phonenum, phoneNumber);
            Assert.assertEquals(no_of_Stores, store_count);

        } catch (AssertionError e) {


        }
        try {
            Assert.assertTrue(line.equals("HTTP/1.1 200 OK"), "Status Line is incorrect");


        } catch (AssertionError e1) {

        }


    }*/

    }

}
