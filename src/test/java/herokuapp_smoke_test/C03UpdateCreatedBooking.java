package herokuapp_smoke_test;

import base_urls.HerokuApprBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;

import static herokuapp_smoke_test.C01CreateBooking.bookinId;
import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class C03UpdateCreatedBooking extends HerokuApprBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/:id

    And
     {
    "firstname" : "Nazar",
    "lastname" : "Can",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2018-01-01",
        "checkout" : "2019-01-01"
    },
    "additionalneeds" : "kahve"
}
    When
        Send put request

    Then
        Status code is 200

    And
        Body:
{
    "firstname": "Nazar",
    "lastname": "Can",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "kahve"
}
     */

    @Test
    public void updateCreatedBooking(){
    spec.pathParams("first","booking"
    ,"second",bookinId);

        BookingDatesPojo bookingDates = new BookingDatesPojo("2018-01-01","2019-01-01");
        BookingDatesPojo payLoad = new BookingDatesPojo("Nazar","Can",111,true,bookingDates,"kahve");

      Response response = given(spec).body(payLoad).when().put("{first}/{second}");
      response.prettyPrint();

        BookingDatesPojo actualData = response.as(BookingDatesPojo.class);

        assertEquals(200,response.statusCode());
        assertEquals(payLoad.getFirstname(),actualData.getFirstname());
        assertEquals(payLoad.getLastname(),actualData.getLastname());
        assertEquals(payLoad.getTotalprice(),actualData.getTotalprice());
        assertEquals(payLoad.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(bookingDates.getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(payLoad.getBookingdates().getCheckout(),actualData.getBookingdates().getCheckout());
        assertEquals(payLoad.getAdditionalneeds(),actualData.getAdditionalneeds());
























    }
}
