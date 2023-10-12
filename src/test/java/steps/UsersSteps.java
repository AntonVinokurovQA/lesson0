package steps;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class UsersSteps {
    public static final RequestSpecification REQUEST_USER = new RequestSpecBuilder()
                .setBaseUri("https://reqres.in/api")
                .setBasePath("/users")
                .setContentType(ContentType.JSON)
                .build();

    public static final ResponseSpecification RESPONSE_200_OK = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();

    public static final ResponseSpecification RESPONSE_201_OK = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .expectContentType(ContentType.JSON)
            .build();

    public static void installSpecification(RequestSpecification request, ResponseSpecification response){
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;
    }
}
