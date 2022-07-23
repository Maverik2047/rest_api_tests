import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ReqresInTests extends TestBase {
    @Test
    public void singleUserNotFound() {
        given()
                .log().uri()
                .log().body()
                .when()
                .get("/api/users/23")
                .then()
                .log().all()
                .statusCode(404);
    }

    @Test
    public void getListUsers() {
        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .when()
                .get("/api/users?page=2")
                .then()
                .log().all()
                .statusCode(200)
                .body("total_pages", is(2));
    }

    @Test
    public void createUser() {
        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body("{ \"name\": \"morpheus\"}")
                .when()
                .post("/api/users")
                .then()
                .log().all()
                .statusCode(201)
                .body("name", is("morpheus"));

    }

    @Test
    public void deleteUser() {
        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .when()
                .delete("/api/users/2")
                .then()
                .log().all()
                .statusCode(204);
    }

    @Test
    public void patchUser() {
        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body("{ \"name\": \"morpheus\",\"job\": \"zion resident\"}")
                .when()
                .patch("/api/users/2")
                .then()
                .log().all()
                .statusCode(200)
                .body("job", is("zion resident"));
    }

    @Test
    public void postLoginUnsuccessful() {
        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body("{\"email\": \"peter@klaven\"}")
                .when()
                .post("/api/login")
                .then()
                .statusCode(400)
                .body("error", is("Missing password"));

    }

    @Test
    public void getSingleResourseYear() {
        Integer expectedYear = 2001;
        Integer actualYear = given()
                .log().uri()
                .when()
                .get("/api/unknown/2")
                .then()
                .log().body()
                .statusCode(200)
                .extract()
                .path("data.year");
        assertEquals(expectedYear, actualYear);


    }

    @Test
    public void getSingleResourseColor() {
        String expectedColor = "#C74375";
        String actualColor = given()
                .log().body()
                .when()
                .get("/api/unknown/2")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .path("data.color");
        assertEquals(expectedColor, actualColor);
    }

}
