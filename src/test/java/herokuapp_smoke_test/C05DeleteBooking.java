package herokuapp_smoke_test;

import base_urls.HerokuApprBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static herokuapp_smoke_test.C01CreateBooking.bookinId;
import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class C05DeleteBooking extends HerokuApprBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/:id
    When
        Send delete request
    Then
        Status code is 201
    And
        Body should be : "Created"
     */
    @Test
    public void deleteBookingTest(){
       spec.pathParams("first","booking","second",bookinId) ;

       String expectedData = "Created";

       Response response = given(spec).when().delete("{first}/{second}");
       response.prettyPrint();

       String actualData = response.asString();
       assertEquals(201,response.statusCode());
       assertEquals(expectedData,actualData);






















    }
}
