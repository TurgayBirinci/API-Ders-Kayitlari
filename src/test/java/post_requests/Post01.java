package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class Post01 extends JsonPlaceHolderBaseUrl {
          /*
         Given
           1) https://jsonplaceholder.typicode.com/todos
           2)  {
                 "userId": 55,
                 "title": "Tidy your room",
                 "completed": false
              }
        When
            I send POST Request to the Url

        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */
        @Test
    public void post() {
            // Url Hazırlanır
            spec.pathParam("first", "todos");

            // Beklenen Datayı Oluştur:
            String payLoad = "{\n" +
                    " \"userId\": 55,\n" +
                    " \"title\": \"Tidy your room\",\n" +
                    " \"completed\": false\n" +
                    "}";

            // Request Gönderip Response Alınır:
            Response response = given(spec).body(payLoad).when().post("{first}");
            response.prettyPrint();

            // Doğrulamalar yapılır:
            JsonPath json = response.jsonPath();

            assertEquals(201, response.statusCode());
            assertEquals(55, json.getInt("userId"));
            assertEquals("Tidy your room", json.getString("title"));
            assertEquals(false, json.getBoolean("completed"));
        }

            @Test
            public void postMap(){
            // Url Oluşturulacak
                spec.pathParam("first","todos");

            // Beklenen Datayı oluştur

                Map<String , Object> payLoad = new HashMap<>();
                payLoad.put("userId",55);
                payLoad.put("title","Tidy your room");
                payLoad.put("completed",false);
                System.out.println(payLoad);

            // Request Gönder Response al
                Response response = given(spec).body(payLoad).when().post("{first}");  // Serialization: Java objesini Json Objesine dönüştürme işlemine denir
                response.prettyPrint();                                                   // Serialization, Serializer denen JAckson Databind, Gson , Yasson gibi dependencyleri pom a yüklemek ile halledilir

            // Doğrulamalar yapılır:
                Map<String,Object> actualData = response.as(HashMap.class);
                System.out.println(actualData);

                assertEquals(201,response.statusCode());
                assertEquals(payLoad.get("userId"),actualData.get("userId"));
                assertEquals(payLoad.get("title"),actualData.get("title"));
                assertEquals(payLoad.get("completed"),actualData.get("completed"));


            }
}
