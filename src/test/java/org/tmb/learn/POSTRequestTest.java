package org.tmb.learn;

import static io.restassured.RestAssured.*;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;
import org.tmb.lomboktest.EmpLombok;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class POSTRequestTest {

    //1. Passing JSON Request body as String
    //   -> Not recommended as when Body becomes large then code becomes clumsy.
    @Test(priority = -1, enabled = false)
    public void testPOSTReqFromString() {
        String reqBody = "{\n" +
                "  \"name\": \"Rahul\",\n" +
                "  \"job\": \"Electrical\"\n" +
                "}";
        Response response = given().
                  header("Content-Type","text/plain").
                  body(reqBody).
                  when().
                  log().all().
                  post("https://reqres.in/api/users");
        response.prettyPrint();
        response.then().statusCode(201);
    }

    @Test(priority = 0, enabled = false)
    public void testPOSTReqFromExternalFile() {
        Response response = given().
                header("Content-Type","text/plain").
                body(new File(System.getProperty("user.dir")+"/src/test/resources/TestData.json")).
                when().
                log().all().
                post("https://reqres.in/api/users");
        response.prettyPrint();
        response.then().statusCode(201);
    }

    @Test(priority = 0, enabled = false)
    public void testPOSTReqFromExternalFileUsingBytesAndConvertToString() throws IOException {
        Faker faker=new Faker(new Locale("IN"));
        byte[] bytes = Files.readAllBytes(Paths.get(System.getProperty("user.dir")+"/src/test/resources/TestData.json"));
        String jsonReqBody = new String(bytes);
        String dynamicJsonReqBody=jsonReqBody.replace("Bipin", faker.name().firstName())
                                             .replace("Software", faker.job().field());

        Response response = given().
                header("Content-Type","text/plain").
                body(dynamicJsonReqBody).
                when().
                log().all().
                post("https://reqres.in/api/users");
        response.prettyPrint();
        response.then().statusCode(201);
    }

    @Test(priority = 1, enabled = false)
    public void testPOSTReqUsingMap() {
        Faker faker=new Faker(new Locale("IN"));
        Map<String,Object> linkedMapJSONBody = new LinkedHashMap<>();
        linkedMapJSONBody.put("first_name", String.valueOf(faker.name().firstName()));
        linkedMapJSONBody.put("last_name",String.valueOf(faker.name().lastName()));
        linkedMapJSONBody.put("email",String.valueOf(faker.name().firstName())+"@gmail.com");
        Response response = given().
                header("Content-Type", ContentType.JSON).
                body(linkedMapJSONBody).
                when().
                log().all().
                post("http://localhost:3000/employees");
        response.prettyPrint();
        response.then().statusCode(201);
    }

    @Test(priority = 1, enabled = false)
    public void testPOSTReqUsingMapandList() {
        Faker faker=new Faker(new Locale("IN"));
        Map<String,Object> mapMain = new LinkedHashMap<>();
        mapMain.put("id",faker.number().numberBetween(1,50));
        mapMain.put("first_name",faker.name().firstName());
        mapMain.put("last_name",faker.name().lastName());
        mapMain.put("email",String.valueOf(faker.name().firstName())+"@gmail.com");

        List<String> rolesList = new ArrayList<>();
        String roleA=faker.job().title();
        String roleB=faker.job().title();
        mapMain.put("roles",Arrays.asList(roleA,roleB));

        List<Object> roleTenureList = new ArrayList<>();
        Map<String,Object> mapRoleA = new LinkedHashMap<>();
        mapRoleA.put("role",roleA);
        mapRoleA.put("tenure","2015 to 2019");
        Map<String,Object> mapRoleB = new LinkedHashMap<>();
        mapRoleB.put("role",roleB);
        mapRoleB.put("tenure","2019 to 2022");
        roleTenureList.add(mapRoleA);
        roleTenureList.add(mapRoleB);
        mapMain.put("roleTenure",roleTenureList);

        Response response = given().
                header("Content-Type", ContentType.JSON).
                body(mapMain).
                when().
                log().all().
                post("http://localhost:3000/employees");
        response.prettyPrint();
        response.then().statusCode(201);
        //This method will fail as this request was built only to learn how to construct Json Request using Map & List.
    }

    @Test(priority = 1, enabled = false)
    public void testPOSTReqUsingJSONObjectandArray() {
        Faker faker=new Faker();
        JSONObject jsonObject = new JSONObject();
        jsonObject.putOpt("id",faker.number().numberBetween(1,20));
        jsonObject.putOpt("first_name",faker.name().firstName());
        jsonObject.putOpt("last_name",faker.name().lastName());
        jsonObject.putOpt("email",String.valueOf(faker.name().firstName())+"@gmail.com");
        jsonObject.accumulate("email",String.valueOf(faker.name().firstName())+"@gmail.com");


        JSONArray jsonArray = new JSONArray();
        String roleA=faker.job().title();
        String roleB=faker.job().title();
        jsonArray.put(roleA);
        jsonArray.put(roleB);
        jsonObject.putOpt("roles",jsonArray);

        String jsonBody = jsonObject.toString();
//        Map<String, Object> jsonBody = jsonObject.toMap();

        Response response = given().
                header("Content-Type", ContentType.JSON).
                body(jsonBody).
                when().
                log().all().
                post("http://localhost:3000/employees");
//        response.prettyPrint();
        response.then().statusCode(201);
    }

    @Test(priority = 2, enabled = false)
    public void testPOSTReqUsingPOJO() {
        Faker faker=new Faker();
        String roleA=faker.job().title();
        String roleB=faker.job().title();

        RoleTenure roleTenure1 = new RoleTenure(roleA,"2015 to 2019");
        RoleTenure roleTenure2 = new RoleTenure(roleB,"2019 to 2022");

        Employee employee = new Employee(   faker.number().numberBetween(1,80),
                                            faker.name().firstName(),
                                            faker.name().lastName(),
                                      faker.name().firstName()+"@gmail.com",
                                            Arrays.asList(roleA,roleB),
                                            Arrays.asList(roleTenure1,roleTenure2));
        Response response = given().
                header("Content-Type", ContentType.JSON).
                body(employee).
                when().
                log().all().
                post("http://localhost:3000/employees");
        response.prettyPrint();
        response.then().statusCode(201);
    }

    @Test(priority = 2, enabled = true)
    public void testPOSTReqUsingPOJOandLombok() {
        Faker faker=new Faker();
        String roleA=faker.job().title();
        String roleB=faker.job().title();

        EmpLombok empLok = new EmpLombok(   faker.number().numberBetween(1,2000),
                                            faker.name().firstName(),
                                            faker.name().lastName(),
                                      faker.name().firstName()+"@gmail.com",
                                            Arrays.asList(roleA,roleB));
        Response response = given().
                baseUri("http://localhost:3000").
                header("Content-Type", ContentType.JSON).
                body(empLok).
                when().
                log().all().
                post("/employees");
        response.prettyPrint();
        response.then().statusCode(201);
    }

    @Test(priority = 2, enabled = false)
    public void testPOSTReqUsingPOJOandLombokForJSONAnnotations() {
        Faker faker=new Faker();
        String roleA=faker.job().title();
        String roleB=faker.job().title();

        EmpLombok empLok = new EmpLombok(   faker.number().numberBetween(1,2000),
                                            null,
                                            "",
                                            faker.name().firstName()+"@gmail.com",
                                            Arrays.asList(roleA,roleB));
        Response response = given().
                baseUri("http://localhost:3000").
                header("Content-Type", ContentType.JSON).
                body(empLok).
                when().
                log().all().
                post("/employees");
        response.prettyPrint();
        response.then().statusCode(201);
    }


}
