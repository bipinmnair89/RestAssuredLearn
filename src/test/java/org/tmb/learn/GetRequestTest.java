package org.tmb.learn;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class GetRequestTest {

    @Test (priority = -1, enabled = true)
    public void testSimpleGETRequest() {
        Response response = given().
                            when().
                            log().all().
                            get("https://reqres.in/api/users/2");
        response.prettyPrint();
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,200);
    }

    @Test (priority = 0, enabled = true)
    public void printResponseData() {
        Response response = given().
                            when().
                            get("https://reqres.in/api/users/2");
        System.out.println(response.getStatusCode());
        System.out.println(response.getTimeIn(TimeUnit.MILLISECONDS));
        System.out.println(response.getContentType());
        System.out.println(response.getHeader("Connection"));
        System.out.println("*****************************************************");
        Headers headers = response.getHeaders();
        for(Header header : headers) {
            System.out.println(header.getName() +" : "+ header.getValue());
        }
        System.out.println("*****************************************************");
    }

    @Test (priority = 1, enabled = true)
    public void testHamcrestAssertions() {
        Response response = given().
                            when().
                            log().all().
                            get("https://reqres.in/api/users/2");
        response.then().statusCode(200);
        response.then().body("data.first_name",containsString("Janet"));
        response.then().body("data.email",equalTo("janet.weaver@reqres.in"));
    }


}
