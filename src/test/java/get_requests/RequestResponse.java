package get_requests;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class RequestResponse {
    /*
        1) Manuel testler için Postman kulllanacağız
        2) Otomasyon testleri için Rest Assured Library kulllanacağız
        3) Test caseler yazılırken şu adımları takip edelim:
            i) Önkoşullar iyi anlaşılmalı
            ii) Test case yazılırken Gherkin dili kullanılır:
                Given : Önkoşullar -----> url , body, ....
                When: Aksiyon -----> get(), post() ...
                Then: Doğrulamalar
                And: Çoklu durumları birbirine bağlamakta kullanılır

        4) Otomasyon testi yazarken aşağıdaki adımları izleyebiliriz:
            i) Url kurulacak
            ii) Beklenen data belirlenecek
            iii) Request gönderilip Response alınacak
            iv) Doğrulamalar yapılacak

     */

    public static void main(String[] args) {

        // i) Url kurulacak
        String url = "https://petstore.swagger.io/v2/pet/5";

        // Base Url : https://petstore.swagger.io/v2
        // 1. path parametresi : /pet
        // 2. path parametresi : /5

        //ii) Beklenen data belirlenecek -----> şimdilik atlayacağız
        //iii) Request gönderilip Response alınacak
        Response response =  given().when().get(url);
        response.prettyPrint();

        // Status code'a nasıl ulaşılır / yazdırılır:
        int statusCode =  response.statusCode();
        System.out.println("statusCode = " + statusCode);
        System.out.println(response.statusCode());
        System.out.println("----------------------------------");

        // Status Line'a nasıl ulaşılır / yazdırılır:
        System.out.println(response.statusLine());
        System.out.println("----------------------------------");

        // ContentType' nasıl ulaşılır
        System.out.println(response.contentType());
        System.out.println("----------------------------------");

        // Header değerlerine nasıl ulaşılır:
        System.out.println(response.header("Server"));
        System.out.println(response.header("Date"));
        System.out.println("----------------------------------");

        // Bütün headerlar nasıl alınır:
        System.out.println(response.headers());
        System.out.println("----------------------------------");

        // Response süresi ni çağırma:
        System.out.println(response.time());
        System.out.println("----------------------------------");


    }

}
