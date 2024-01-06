package post_requests;

import base_urls.HerokuApprBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerokuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class Post03 extends HerokuApprBaseUrl {
        /*
Given
1) https://restful-booker.herokuapp.com/booking
2) {
    "firstname": "John",
    "lastname": "Doe",
    "totalprice": 11111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2021-09-09",
        "checkout": "2021-09-21"
     }
  }
When
I send POST Request to the Url
Then
Status code is 200
And response body should be like {
                                   "bookingid": 5315,
                                   "booking": {
                                       "firstname": "John",
                                       "lastname": "Doe",
                                       "totalprice": 11111,
                                       "depositpaid": true,
                                       "bookingdates": {
                                           "checkin": "2021-09-09",
                                           "checkout": "2021-09-21"
                                       }
                                   }
                                }
*/

    @Test
    public void post(){
        // Url kurulacak:
        spec.pathParam("first","booking");

        // Beklenen data kurulacak:

        Map<String ,String> bookingMap = HerokuAppTestData.bookingMapper("2021-09-09","2021-09-21");
        Map<String, Object> payLoad = HerokuAppTestData.herokuAppMapper("John","Doe", 11111,true,bookingMap,null);
        System.out.println("payLoad = " + payLoad);

        // Request------Respons:
        Response response = given(spec).body(payLoad).when().post("{first}");
        response.prettyPrint();

        // Doğrulamalar yapılır:
        JsonPath json = response.jsonPath();
        Map<String,Object> actualData =  response.as(HashMap.class);

        assertEquals(payLoad.get("firstname"),((Map)actualData.get("booking")) .get("firstname"));
        assertEquals(payLoad.get("firstname"),json.getString("booking.firstname"));

        assertEquals(payLoad.get("lastname"),((Map)actualData.get("booking")) .get("lastname"));
        assertEquals(payLoad.get("totalprice"),((Map)actualData.get("booking")) .get("totalprice"));
        assertEquals(payLoad.get("depositpaid"),((Map)actualData.get("booking")) .get("depositpaid"));
        assertEquals(bookingMap.get("checkin"),((Map)((Map)actualData.get("booking")) .get("bookingdates")).get("checkin"));
        assertEquals(bookingMap.get("checkin"),json.getString("booking.bookingdates.checkin"));

        assertEquals(bookingMap.get("checkout"),((Map)((Map)actualData.get("booking")) .get("bookingdates")).get("checkout"));
}
}
