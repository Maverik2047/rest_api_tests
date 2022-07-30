import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class DemoWebShopTests {

    @Test
    void addToCart(){
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie("Nop.customer=a34a99db-947a-41ac-8fa3-9eaac88ed4b3; ARRAffinity=92eb765899e80d8de4d490df907547e5cb10de899e8b754a4d5fa1a7122fad69")
                .body("addtocart_31.EnteredQuantity=1")
                .log().all()
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/31/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("success",is(true))
                .body("message",is("The product has been added to your <a href=\"/cart\">shopping cart</a>"));

    }
}
