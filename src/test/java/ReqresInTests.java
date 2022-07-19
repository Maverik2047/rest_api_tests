import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class ReqresInTests {
    @Test
    public void SingleUserNotFound(){
        given()
                .log().uri()
                .log().body()
                .when()
                .get("https://reqres.in/api/users/23")
                .then()
                .log().all()
                .statusCode(404);
    }
}
