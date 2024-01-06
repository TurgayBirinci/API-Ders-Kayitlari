package herokuapp_smoke_test;

import base_urls.HerokuApprBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.HerokuRootPojo;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class C01CreateBooking extends HerokuApprBaseUrl {
    public static int bookinId;


        /*
    Given
        https://restful-booker.herokuapp.com/booking
    And
        {
    "firstname" : "Veli",
    "lastname" : "Can",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2018-01-01",
        "checkout" : "2019-01-01"
    },
    "additionalneeds" : "Koy Kahvaltısı"
}
     When
        Send post request

     Then
        Status code is 200

     And
        Body:
      {
    "bookingid": 1046,
    "booking": {
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
}



{
    "bookingid": 1046,
    "booking": {

{
        "firstname": "Veli",
        "lastname": "Can",
        "totalprice": 111,
        "depositpaid": true,
        "bookingdates": , bo....
        "additionalneeds": "Koy Kahvaltısı"
    }

        {
            "checkin": "2018-01-01",
            "checkout": "2019-01-01"
        }
     */

    @Test
    public void createBokkingTest(){
        spec.pathParam("first","booking");

        BookingDatesPojo bookingDates = new BookingDatesPojo("2018-01-01","2019-01-01");
        BookingDatesPojo payLoad = new BookingDatesPojo("Veli","Can",111,true,bookingDates,"Koy Kahvaltısı");

        Response response = given(spec).body(payLoad).when().post("{first}");
        response.prettyPrint();

        HerokuRootPojo actualData = response.as(HerokuRootPojo.class);
        assertEquals(200,response.statusCode());
        assertEquals(payLoad.getFirstname(),actualData.getBooking().getFirstname());
        assertEquals(payLoad.getLastname(),actualData.getBooking().getLastname());
        assertEquals(payLoad.getTotalprice(),actualData.getBooking().getTotalprice());
        assertEquals(payLoad.getDepositpaid(),actualData.getBooking().getDepositpaid());
        assertEquals(bookingDates.getCheckin(),actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(),actualData.getBooking().getBookingdates().getCheckout());
        assertEquals(payLoad.getAdditionalneeds(),actualData.getBooking().getAdditionalneeds());
        bookinId = response.jsonPath().getInt("bookingid");
    }
}
