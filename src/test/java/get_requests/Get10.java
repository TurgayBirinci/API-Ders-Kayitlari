package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import javax.xml.xpath.XPathEvaluationResult;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class Get10 extends JsonPlaceHolderBaseUrl {
    /*
Given
https://jsonplaceholder.typicode.com/todos/2
    When
        I send GET Request to the URL
    Then
        Status code is 200
    And
        "completed" is false
    And
        "userId" is 1
    And     "title" is "quis ut nam facilis et officia qui"
    And     header "Via" is "1.1 vegur"
    And     header "Server" is "cloudflare"
    And     body is like
        {
        "userId": 1,
        "id": 2,
        "title": "quis ut nam facilis et officia qui",
        "completed": false
        }
 */
    @Test
    public void get(){
        // Url Kurulur:
        spec.pathParams("first","todos"
                ,"second",2);


        // Beklenen data kurulur
        Map<String,Object> expectedData = JsonPlaceHolderTestData.jsonPlaceHolderMapper(1,"quis ut nam facilis et officia qui",false);
        expectedData.put("Via","1.1 vegur");
        expectedData.put("Server","cloudflare");

        // Request dönderilip Response Alınır
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        // Doğrulamalar yapılır:
        Map<String,Object> actualData =  response.as(HashMap.class);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals(expectedData.get("Server"),response.header("Server"));
        assertEquals(expectedData.get("Via"),response.header("Via"));

        // header "Via" is "1.1 vegur"
        assertEquals("1.1 vegur",response.header("Via"));

        // header "Server" is "cloudflare"
        assertEquals("cloudflare",response.header("Server"));
















    }
}