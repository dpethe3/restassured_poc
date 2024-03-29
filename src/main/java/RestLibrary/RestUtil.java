package RestLibrary;

import Common.AutomationFrameworkBase;
import Common.ConfigProperties;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestUtil {

    public static String LLP_URL="";



   public RestUtil() {
        AutomationFrameworkBase base = new AutomationFrameworkBase();
        base.loadConfigProperties("config.properties");

    }
    public String  checkenv(){
      //  LLP_URL="https://momentfeed-prod.apigee.net/api/llp.json?auth_token=";
        if(ConfigProperties.qaenv.equals("qa")){
            LLP_URL="https://momentfeed-prod.apigee.net/api/llp.json?auth_token=";

        }
        else{
            LLP_URL="https://momentfeed-prod.apigee.net/api/llp.json?auth_token=";
        }
        return LLP_URL;
    }

    public Response GetAPICall(String url)
    {
        // Specify the base URL to the RESTful web service
        RestAssured.baseURI = url;

        // Get the RequestSpecification of the request that you want to sent
        // to the server. The server is specified by the BaseURI that we have
        // specified in the above step.
        RequestSpecification httpRequest = RestAssured.given();

        // Make a request to the server by specifying the method Type and the method URL.
        // This will return the Response from the server. Store the response in a variable.
        Response response = httpRequest.request(Method.GET,url );

        // Now let us print the body of the message to see what response
        // we have recieved from the server
        String responseBody = response.getBody().asString();
        //System.out.println("Response Body is =>  " + responseBody);
         return response;
    }

}