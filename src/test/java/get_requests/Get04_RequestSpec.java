package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class Get04_RequestSpec extends JsonPlaceHolderBaseUrl {
    /*
        Given
            https://jsonplaceholder.typicode.com/todos/23
        When
            User send GET Request to the URL
        Then
            HTTP Status Code should be 200
		And
		    Response format should be "application/json"
		And
		    "title" is "et itaque necessitatibus maxime molestiae qui quas velit",
		And
		    "completed" is false
		And
		    "userId" is 2
 */

    @Test
    public void get(){
        // i)  Url belirlenir
        spec.pathParams("first","todos"
                ,"second",23);

        // ii) Beklenen data belirlenir
        // iii) Request gönderilip Response alınır

        Response response =  given(spec).when().get("{first}/{second}");  // "{}/{}/{}"

        response.prettyPrint();

        // iv)) Doğrulamalar yapılır

        // Body de hard assertion yapacak isek her doğrulamada farklı body metodları kullanılır
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit"))
                .body("title",is("et itaque necessitatibus maxime molestiae qui quas velit"))
                .body("completed",equalTo(false))
                .body("userId",equalTo(2));

        // Soft Assertion için tüm body sorgulamaları tek bir body metodu içinde yapılır
        response
                .then()
                .body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit")
                        ,"completed",equalTo(false)
                        ,"userId",equalTo(2));
    }


}