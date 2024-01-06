package herokuapp_smoke_test;

import base_urls.HerokuApprBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;

import static herokuapp_smoke_test.C01CreateBooking.bookinId;
import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class C02GetCreatedBooking extends HerokuApprBaseUrl {
        /*
    Given
        https://restful-booker.herokuapp.com/booking/:id
    When
        Send get request
    Then
        Status code is 200
    And
        Body:
            {
    "firstname": "Veli",
    "lastname": "Can",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "Koy Kahvaltısı"
}
     */
    @Test
    public void getCreatedBookingTest(){
    spec.pathParams("first","booking","second",bookinId);

        BookingDatesPojo bookingDates = new BookingDatesPojo("2018-01-01","2019-01-01");
        BookingDatesPojo expectedData = new BookingDatesPojo("Veli","Can",111,true,bookingDates,"Koy Kahvaltısı");

        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        BookingDatesPojo actualData = response.as(BookingDatesPojo.class);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(bookingDates.getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(expectedData.getBookingdates().getCheckout(),actualData.getBookingdates().getCheckout());



















    }

}
