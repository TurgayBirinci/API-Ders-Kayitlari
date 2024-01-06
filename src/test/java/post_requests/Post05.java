package post_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.HerokuRootPojo;

import static io.restassured.RestAssured.given;

public class Post05 extends HerOkuAppBaseUrl {
         /*
         Given
          1)  https://restful-booker.herokuapp.com/booking
          2)   {
                "firstname": "Ali",
                "lastname": "Can",
                "totalprice": 999,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2021-09-21",
                    "checkout": "2021-12-21"
                 },
                 "additionalneeds": "Breakfast"
             }
        When
 		    I send POST Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
 		                            "bookingid": 16,
 		                            "booking" :{
                                        "firstname": "Ali",
                                        "lastname": "Can",
                                        "totalprice": 999,
                                        "depositpaid": true,
                                        "bookingdates": ,bookingDates
                                        "additionalneeds": "Breakfast"
                                     }
                                  }
     */

    @Test
    public void post(){
        spec.pathParam("first","booking");

        BookingDatesPojo bookingDates= new BookingDatesPojo("2021-09-21","2021-12-21");
        BookingPojo payLoad = new BookingPojo("Ali","Can",999,true,bookingDates,"Breakfast");

        Response response = given(spec).body(payLoad).when().post("{first}");
        response.prettyPrint();

        HerokuRootPojo actualData =response.as(HerokuRootPojo.class);

        System.out.println("actualData.getBookingid() = " + actualData.getBookingid());
        System.out.println("actualData.getBooking().getFirstname() = " + actualData.getBooking().getFirstname());

    }

}
