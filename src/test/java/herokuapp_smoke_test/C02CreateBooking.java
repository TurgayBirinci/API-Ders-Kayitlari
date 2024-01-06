package herokuapp_smoke_test;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.HerokuRootPojo;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class C02CreateBooking extends HerOkuAppBaseUrl {
    /*
    Test Case:  Booking oluşturma
    Given
        https://restful-booker.herokuapp.com/booking
    And
        body: {
                "firstname" : "Ali",
                "lastname" : "Can",
                "totalprice" : 111,
                "depositpaid" : true,
                "bookingdates" : {
                    "checkin" : "2018-01-01",
                    "checkout" : "2019-01-01"
                },
                "additionalneeds" : "Kahvalti"
}
    When
        user send Post request
    Then
        validates Status code is 200
    And
        body is like
                        {
                        "bookingid": 1,
                        "booking": {
                            "firstname": "Ali",
                            "lastname": "Can",
                            "totalprice": 111,
                            "depositpaid": true,
                            "bookingdates": {
                                "checkin": "2018-01-01",
                                "checkout": "2019-01-01"
                            },
                            "additionalneeds": "Kahvalti"
                        }
                        }

     */
    @Test
    public  void bookingOlusturTest(){
        spec.pathParam("first","booking");

        BookingDatesPojo bookingDates= new BookingDatesPojo("2018-01-01","2019-01-01");
        BookingDatesPojo payLoad = new BookingDatesPojo("Ali","Can",111,true,bookingDates,"Kahvalti");

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















    }
}
