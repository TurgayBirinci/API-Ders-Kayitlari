package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class JsonPlaceHolderBaseUrl {

    protected RequestSpecification spec;

    @Before
    public void setUp(){
        String url = "https://jsonplaceholder.typicode.com/todos/23";
        spec = new RequestSpecBuilder().setBaseUri(url).build();
    }



}
