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
import stepDefinitions.setup.BaseTest;

import java.util.List;

import static io.restassured.RestAssured.given;
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

    private RequestSpecification request;

    private Response response;


    @Given("que administrador del aplicativo restful booker")
    public void queAdministradorDelAplicativoRestfulBooker() {
        try{
            generalSetUp();
            request = given();
        }catch(Exception exception){
            Assertions.fail(exception.getMessage(),exception);
            LOGGER.error(exception.getMessage(),exception);
        }
    }
    @When("doy click en el boton listar reservas")
    public void doyClickEnElBotonListarReservas() {
        try {
            response = request.when().get();
        }catch (Exception exception) {
            Assertions.fail(exception.getMessage(),exception);
            LOGGER.error(exception.getMessage(),exception);
        }
    }
    @Then("el restful-booker sistema lista todas las reservas encontradas.")
    public void elRestfulBookerSistemaListaTodasLasReservasEncontradas() {
        try{
            response.then().assertThat().contentType(ContentType.JSON).statusCode(HttpStatus.SC_OK);
            List<Integer> jsonResponse = response.jsonPath().getList("bookingid");
            assertFalse(jsonResponse.contains(null));
            LOGGER.info(jsonResponse);
        }catch(Exception exception){
            Assertions.fail(exception.getMessage(),exception);
            LOGGER.error(exception.getMessage(),exception);
        }
    }

}
