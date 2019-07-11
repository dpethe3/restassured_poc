import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ApiCall_Gherkin {

    @Test
    public void checkStatusCode() {

        given().

                when().
                get("https://momentfeed-prod.apigee.net/api/llp.json?auth_token=RCKBBWJSPPOONTUT").
                then().
                assertThat().
               statusCode(200);
    }


}
