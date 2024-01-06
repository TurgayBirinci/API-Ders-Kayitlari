package get_requests;

import base_urls.HerokuApprBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerokuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.Matchers.equalTo;
import static test_data.HerokuAppTestData.bookingMapper;
import static test_data.HerokuAppTestData.herokuAppMapper;

public class Get11_NestedMap extends HerokuApprBaseUrl {
         /*
        Given
            https://restful-booker.herokuapp.com/booking/50
        When
            I send GET Request to the url
        Then
            Response body should be like that;
                {
                    "firstname": "Jane",
                    "lastname": "Doe",
                    "totalprice": 111,
                    "depositpaid": true,
                    "bookingdates": {
                        "checkin": "2018-01-01",
                        "checkout": "2019-01-01"
                    },
                    "additionalneeds": "Extra pillows please"
                }
     */
    @Test
    public void get(){
        // Url Kurulur:
       spec.pathParams("first","booking"
       ,"second",50) ;

       // Beklenen data kurulur:

        // Nested yapılarda beklenen data en içteki yapıdan başlayarak oluşturulur

        Map<String,String> bookingMap = new HashMap<>();
        bookingMap.put("checkin","2018-01-01");
        bookingMap.put("checkout","2019-01-01");
        System.out.println("bookingMap = " + bookingMap);

        Map<String,Object> expectedData = new HashMap<>();
        expectedData.put("firstname","Jane");
        expectedData.put("lastname","Doe");
        expectedData.put("totalprice",111);
        expectedData.put("depositpaid",true);
        expectedData.put("bookingdates",bookingMap);
        expectedData.put("additionalneeds","Extra pillows please");
        System.out.println("expectedData = " + expectedData);

        // Request---- Response
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();




        // Doğrulamalar yapılır:
       response
               .then()
               .statusCode(200)
               .body("firstname", equalTo(expectedData.get("firstname")))
               .body("bookingdates.checkin",equalTo(bookingMap.get("checkin")));

        JsonPath json = response.jsonPath();
        assertEquals(expectedData.get("firstname"),json.getString("firstname"));
        assertEquals(bookingMap.get("checkin"),json.getString("bookingdates.checkin"));

        Map<String,Object> actualData = response.as(HashMap.class);
        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"),actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"),actualData.get("depositpaid"));
        assertEquals(((Map)expectedData.get("bookingdates")).get("checkin") ,((Map)actualData.get("bookingdates")) .get("checkin"));
        assertEquals(bookingMap.get("checkout"),((Map)actualData.get("bookingdates")).get("checkout"));
        assertEquals(expectedData.get("additionalneeds"),actualData.get("additionalneeds"));
 }
  @Test
 public void get11b(){
   // Url Kurulur:
   spec.pathParams("first","booking"
           ,"second",50) ;

   // Beklenen data kurulur:

   // Nested yapılarda beklenen data en içteki yapıdan başlayarak oluşturulur

   Map<String,String> bookingMap = bookingMapper("2018-01-01","2019-01-01");
   System.out.println("bookingMap = " + bookingMap);

   Map<String,Object> expectedData = herokuAppMapper("Jane","Doe",111,true,bookingMap,"Extra pillows please");
   System.out.println("expectedData = " + expectedData);

   // Request---- Response
   Response response = given(spec).when().get("{first}/{second}");
   response.prettyPrint();




   // Doğrulamalar yapılır:
   response
           .then()
           .statusCode(200)
           .body("firstname", equalTo(expectedData.get("firstname")))
           .body("bookingdates.checkin",equalTo(bookingMap.get("checkin")));

   JsonPath json = response.jsonPath();
   assertEquals(expectedData.get("firstname"),json.getString("firstname"));
   assertEquals(bookingMap.get("checkin"),json.getString("bookingdates.checkin"));

   Map<String,Object> actualData = response.as(HashMap.class);
   assertEquals(200,response.statusCode());
   assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
   assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
   assertEquals(expectedData.get("totalprice"),actualData.get("totalprice"));
   assertEquals(expectedData.get("depositpaid"),actualData.get("depositpaid"));
   assertEquals(((Map)expectedData.get("bookingdates")).get("checkin") ,((Map)actualData.get("bookingdates")) .get("checkin"));
   assertEquals(bookingMap.get("checkout"),((Map)actualData.get("bookingdates")).get("checkout"));
   assertEquals(expectedData.get("additionalneeds"),actualData.get("additionalneeds"));
  }



}
