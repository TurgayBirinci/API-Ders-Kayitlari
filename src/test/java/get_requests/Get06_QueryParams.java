package get_requests;

import base_urls.HerokuApprBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class Get06_QueryParams extends HerokuApprBaseUrl {
    /*
Given
   https://restful-booker.herokuapp.com/booking
When
   User sends get request to the URL
Then
   Status code is 200
And
   Among the data there should be someone whose firstname is "John" and lastname is "Smith"
*/
    @Test
    public void get(){
        //i)  Url belirlenir
        spec.pathParam("first","booking")
                .queryParams("firstname","John"
                        ,"lastname","Smith");

        //ii) Beklenen data belirlenir
        //iii) Request gönderilip Response alınır
        Response response = given(spec).when().get("{first}");
        response.prettyPrint();

        //iv)) Doğrulamalar yapılır
        response
                .then()
                .statusCode(200)
                .body(containsString("bookingid"))
                .body("bookingid",hasSize(greaterThan(0)));

        assertEquals(200,response.statusCode());
        String responseStr = response.asString();
        System.out.println(responseStr);

        assertTrue(response.asString().contains("bookingid"));
    }

}