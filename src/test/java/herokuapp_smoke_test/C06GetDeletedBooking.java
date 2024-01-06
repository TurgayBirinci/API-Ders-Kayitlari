package herokuapp_smoke_test;

import base_urls.HerokuApprBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import static herokuapp_smoke_test.C01CreateBooking.bookinId;
import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class C06GetDeletedBooking extends HerokuApprBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/:id
    When
        Send get request
    Then
        Status code is 404
    And
        Body is "Not Found"
     */
    @Test
    public void confirmDeleteTest(){
    spec.pathParams("first","booking","second",bookinId);

    String expectedData = "Not Found";

    Response response = given(spec).when().get("{first}/{second}");
    response.prettyPrint();

    String actualData = response.asString();
        assertEquals(404,response.statusCode());
        assertEquals(expectedData,actualData);
  }

}
