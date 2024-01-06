package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;


import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;
import static test_data.JsonPlaceHolderTestData.jsonPlaceHolderMapper;

public class Post02 extends JsonPlaceHolderBaseUrl {

    // Post01 deki task map oluşturmayı metod içinde hallederek geliştirildi
    @Test
    public void postMap(){
        // Url Oluşturulacak
        spec.pathParam("first","todos");

        // Beklenen Datayı oluştur
        Map<String,Object> payLoad = jsonPlaceHolderMapper(55,"Tidy your room",false);


        // Request Gönder Response al
        Response response = given(spec).body(payLoad).when().post("{first}");  // Serialization: Java objesini Json Objesine dönüştürme işlemine denir
        response.prettyPrint();                                                   // Serialization, Serializer denen JAckson Databind, Gson , Yasson gibi dependencyleri pom a yüklemek ile halledilir

        // Doğrulamalar yapılır:
        Map<String,Object> actualData = response.as(HashMap.class);  // Desirialization : Json Objesini Java Objesine dönüştürmek
        System.out.println(actualData);

        assertEquals(201,response.statusCode());
        assertEquals(payLoad.get("userId"),actualData.get("userId"));
        assertEquals(payLoad.get("title"),actualData.get("title"));
        assertEquals(payLoad.get("completed"),actualData.get("completed"));


    }
}
