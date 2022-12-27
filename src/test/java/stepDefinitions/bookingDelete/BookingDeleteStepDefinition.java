package stepDefinitions.bookingDelete;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.apache.log4j.Hierarchy;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import runner.BookingAllRunner;
import runner.BookingDeleteRunner;
import stepDefinitions.setup.BaseTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class BookingDeleteStepDefinition extends BaseTest {

    private static final Logger LOGGER = Logger.getLogger(BookingDeleteStepDefinition.class);

    private RequestSpecification requestPost;
    private Response responsPost;
    private RequestSpecification requestDelete;
    private Response responsDelete;

    @Given("que el usuario del aplicativo restful booker tiene reservas cradas")
    public void que_el_usuario_del_aplicativo_restful_booker_tiene_reservas_cradas() {
        try {
            generalSetUp();
            generarUsuario();
            requestPost = given().body(bodyUser());
            responsPost = requestPost.post();
        }catch (Exception exception){
            Assertions.fail(exception.getMessage(),exception);
            LOGGER.error(exception.getMessage(),exception);
        }
    }
    @When("doy click en el boton eliminar listar reservas")
    public void doy_click_en_el_boton_eliminar_listar_reservas() {
        try {
            requestDelete = given()
                    .header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=");
            String idBooking = responsPost.body().jsonPath().getString("bookingid");
            responsDelete = requestDelete.delete("/" + idBooking);
        }catch (Exception exception){
            Assertions.fail(exception.getMessage(),exception);
            LOGGER.error(exception.getMessage(),exception);
        }
    }
    @Then("el aplicativo restful-booker muestra n mensaje de reserva eliminada")
    public void el_aplicativo_restful_booker_muestra_n_mensaje_de_reserva_eliminada() {
        try {
            assertEquals("Created", responsDelete.body().asString());
            responsDelete.then().statusCode(HttpStatus.SC_CREATED);
        }catch (Exception exception){
            Assertions.fail(exception.getMessage(),exception);
            LOGGER.error(exception.getMessage(),exception);
        }
    }

}
