import io.restassured.http.ContentType;
import jdk.jfr.Description;
import org.hamcrest.Matchers;
import org.junit.Test;
import pojo.requests.NewUser;
import pojo.responses.NewUserResponse;
import pojo.responses.UserData;
import tools.DataGenerator;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ReqresTest {
    /**
     * Tests before refactoring
     */
    @Test
    @Description("Check that count of elements per page is correct")
    public void checkCountOfElementPerPage() {
        given()
                .baseUri("https://reqres.in/api")
                .basePath("/users")
                .queryParam("page", "1")
                .queryParam("per_page", "3")
                .when()
                .get()
                .then()
                .statusCode(200)
                .body("data.size()", Matchers.equalTo(3));
    }

    @Test
    @Description("Check user's emails end with reqres.in")
    public void checkThatUsersHaveValidEmail() {
        List<UserData> users = given()
                .baseUri("https://reqres.in/api")
                .basePath("/users")
                .queryParam("page", "1")
                .queryParam("per_page", "3")
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract().body().jsonPath().getList("data", UserData.class);

        assertTrue(users.stream().allMatch(t -> t.getEmail().endsWith("reqres.in")));
    }

    @Test
    @Description("Check user's emails end with reqres.in")
    public void testCreateUsers() {
        NewUser newUser = DataGenerator.newUserRandom();
        NewUserResponse responseUser = given()
                .baseUri("https://reqres.in/api")
                .basePath("/users")
                .contentType(ContentType.JSON)
                .when()
                .body(newUser)
                .post()
                .then()
                .statusCode(201)
                .extract().as(NewUserResponse.class);

        assertEquals(newUser.getName(), responseUser.getName());
        assertEquals(newUser.getJob(), responseUser.getJob());
    }
}
