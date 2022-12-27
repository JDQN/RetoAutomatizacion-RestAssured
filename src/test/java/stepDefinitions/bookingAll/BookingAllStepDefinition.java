package stepDefinitions.bookingAll;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import runner.BookingAllRunner;
import runner.CreateBookingRunner;
import stepDefinitions.booking.CreateBookingStepDefinition;
import stepDefinitions.setup.BaseTest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
 * La class BookingAllStepDefinition permite interartuar con los stepDefinitions
 * Y extends de BaseTest
 * @Class BookingAllRunner
 * @author <jdquimbayo72@misena.edu.co>
 */
public class BookingAllStepDefinition extends BaseTest {

    private static final Logger LOGGER = Logger.getLogger(BookingAllRunner.class);
    private RequestSpecification requestPost;
    private Response responsPost;
    private RequestSpecification requestGet;
    private Response responseGet;


    @Given("que administrador del aplicativo restful booker")
    public void queAdministradorDelAplicativoRestfulBooker() {
        try{
            generalSetUp();
            requestPost = given();
        }catch(Exception exception){
            Assertions.fail(exception.getMessage(),exception);
            LOGGER.error(exception.getMessage(),exception);
        }
    }
    @When("doy click en el boton listar reservas")
    public void doyClickEnElBotonListarReservas() {
        try {
            responsPost = requestPost.when().get();
        }catch (Exception exception) {
            Assertions.fail(exception.getMessage(),exception);
            LOGGER.error(exception.getMessage(),exception);
        }
    }
    @Then("el restful-booker sistema lista todas las reservas encontradas.")
    public void elRestfulBookerSistemaListaTodasLasReservasEncontradas() {
        try{
            responsPost.then().assertThat().contentType(ContentType.JSON).statusCode(HttpStatus.SC_OK);
            List<Integer> jsonResponse = responsPost.jsonPath().getList("bookingid");
            assertFalse(jsonResponse.contains(null));
            LOGGER.info(jsonResponse);
        }catch(Exception exception){
            Assertions.fail(exception.getMessage(),exception);
            LOGGER.error(exception.getMessage(),exception);
        }
    }


    //Scenario 4
    @Given("que como administrador del aplicativo restful booker necesito validar dichas reservas")
    public void queComoAdministradorDelAplicativoRestfulBookerNecesitoValidarDichasReservas() {
        try {
            generalSetUp();
            generarUsuario();
            requestPost = given().body(bodyUser());
            responsPost = requestPost.post();
            requestGet = given();
            LOGGER.info(responsPost.body().print());
        }catch (Exception exception) {
            Assertions.fail(exception.getMessage(),exception);
            LOGGER.error(exception.getMessage(),exception);
        }
    }
    @When("doy click en el boton ver reserva")
    public void doyClickEnElBotonVerReserva() {
        try{
            String idBooking = responsPost.body().jsonPath().getString("bookingid");
            responseGet = requestGet.get("/" + idBooking);
        } catch (Exception exception){
            Assertions.fail(exception.getMessage(),exception);
            LOGGER.error(exception.getMessage(),exception);
        }
    }
    @Then("el aplicativo restful-booker muestra las reservas encontradas.")
    public void elAplicativoRestfulBookerMuestraLasReservasEncontradas() {
        try{
            String firstname = responseGet.body().jsonPath().getString("firstname");
            String lastname = responseGet.body().jsonPath().getString("lastname");
            Assertions.assertEquals(firstname,usuarioModel.getFirstName());
            Assertions.assertEquals(lastname,usuarioModel.getLastName());
        }catch(Exception exception){
            Assertions.fail(exception.getMessage(),exception);
            LOGGER.error(exception.getMessage(),exception);
        }
    }
}
