import jdk.jfr.Description;
import org.hamcrest.Matchers;
import org.junit.Test;
import pojo.requests.NewUser;
import pojo.responses.NewUserResponse;
import pojo.responses.UserData;
import steps.UsersSteps;
import tools.DataGenerator;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ReqresRefactoredTest {
    /**
     * Tests after refactoring
     */
    @Test
    @Description("Check that count of elements per page is correct")
    public void checkCountOfElementPerPage() {
        UsersSteps.installSpecification(UsersSteps.REQUEST_USER, UsersSteps.RESPONSE_200_OK);

        given()
                .queryParam("page", "1")
                .queryParam("per_page", "3")
                .when()
                .get()
                .then()
                .body("data.size()", Matchers.equalTo(3));
    }

    @Test
    @Description("Check user's emails end with reqres.in")
    public void checkThatUsersHaveValidEmail() {
        UsersSteps.installSpecification(UsersSteps.REQUEST_USER, UsersSteps.RESPONSE_200_OK);

        List<UserData> users = given()
                .queryParam("page", "1")
                .queryParam("per_page", "3")
                .when()
                .get()
                .then()
                .extract().body().jsonPath().getList("data", UserData.class);

        assertTrue(users.stream().allMatch(t -> t.getEmail().endsWith("reqres.in")));
    }

    @Test
    @Description("Check user's emails end with reqres.in")
    public void testCreateUsers() {
        UsersSteps.installSpecification(UsersSteps.REQUEST_USER, UsersSteps.RESPONSE_201_OK);

        NewUser newUser = DataGenerator.newUserRandom();
        NewUserResponse responseUser = given()
                .when()
                .body(newUser)
                .post()
                .then()
                .extract().as(NewUserResponse.class);

        assertEquals(newUser.getName(), responseUser.getName());
        assertEquals(newUser.getJob(), responseUser.getJob());
    }
}
