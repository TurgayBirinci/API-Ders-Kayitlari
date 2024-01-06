package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Get05 extends JsonPlaceHolderBaseUrl {
    /*
        Given
            https://jsonplaceholder.typicode.com/todos
        And
	        Accept type is "application/json"
        When
	 	    I send a GET request to the Url
	    Then
	        HTTP Status Code should be 200
	    And
	        Response format should be "application/json"
	    And
	        There should be 200 todos
	    And
	        "quis eius est sint explicabo" should be one of the todos title
	    And
	        2, 7, and 9 should be among the userIds
     */
    @Test
    public void get(){
        // i)  Url belirlenir
        spec.pathParam("first","todos").accept(ContentType.JSON);

        // ii) Beklenen data belirlenir
        // iii) Request gönderilip Response alınır

        Response response = given(spec).when().get("{first}");
        response.prettyPrint();

        // iv)) Doğrulamalar yapılır

        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                // .body("[0].title",equalTo("delectus aut autem"));   --> list olarak dönen bir yapıda istenilen jsona index ile ulaşabiliriz
                .body("title", hasSize(200)
                        ,"title",hasItem("quis eius est sint explicabo")
                        ,"userId",hasItems(2,7,9));




        // hasSize() metodu liste dönen responsun boyutunu verir
        // hasItem() metodu list şeklindeki responsun özel bir elemanı içerip içermediğini sorgulamada kullanılır
        // hasItems() metodu list şeklindeki responsun çoklu  eleman içerip içermediğini sorgulamada kullanılır
    }
}